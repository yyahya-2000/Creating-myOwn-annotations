package main;

import main.annotation.AnyOf;
import main.annotation.Constrained;
import main.annotation.InRange;
import main.annotation.Size;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class ValidatorClass implements Validator {
    private final Set<ValidationError> validationErrorSet = new LinkedHashSet<>();
    //helps in getting annotations of deep lists
    private boolean stopDigging = false;
    //helps in getting annotations of lists
    private AnnotatedType annotatedType;

    /**
     * @param object the object, which his fields will be checked
     * @return returns Set of detected ValidationErrors
     */
    @Override
    public Set<ValidationError> validate(Object object) {
        try {
            if (!isConstrained(object))
                throw new IllegalArgumentException("Make sure, that the given object isn't null or" +
                        " is annotated by @Constrained annotation");
            for (Field f : getFields(object)) {
                f.setAccessible(true);
                stopDigging = false;
                try {
                    if (isCollection(f.get(object)))
                        annotatedType = f.getAnnotatedType();
                    checkField(f.getName(), f.get(object), getAnnotations(f));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return validationErrorSet;
    }

    /**
     * check if field value meets the condition of his declared annotations
     *
     * @param fieldName   field name
     * @param fieldValue  field value
     * @param annotations field annotations
     */
    private void checkField(String fieldName, Object fieldValue, Annotation[] annotations) {
        for (Annotation ano : annotations) {
            checkAnnotation(fieldName, fieldValue, ano);
        }
        if (isConstrained(fieldValue) && getFields(fieldValue).length > 0)
            handleClassInstance(fieldName, fieldValue);
        if (isCollection(fieldValue)) {
            try {
                handleCollection(fieldName, fieldValue);
            } catch (IllegalAccessException e) {
                System.err.println(e.getMessage());
            }
        } else
            stopDigging = true;
    }

    /**
     * in case that the field is class instance, this method will be called, to handle the situation
     *
     * @param fieldName  field name
     * @param fieldValue filed value
     */
    private void handleClassInstance(String fieldName, Object fieldValue) {
        AnnotatedType temp = annotatedType;
        for (Field f : getFields(fieldValue)) {
            f.setAccessible(true);
            stopDigging = false;
            try {
                if (isCollection(f.get(fieldValue)))
                    annotatedType = f.getAnnotatedType();
                checkField(fieldName + "." + f.getName(), f.get(fieldValue), getAnnotations(f));
            } catch (IllegalAccessException e) {
                System.err.println(e.getMessage());
            }
        }
        annotatedType = temp;
    }


    /**
     * checks if the value of field meets the  annotation conditions and handling the situation whatever it is
     *
     * @param fieldName  filed name
     * @param fieldValue field value
     * @param ano        some annotation
     */
    private void checkAnnotation(String fieldName, Object fieldValue, Annotation ano) {
        try {
            IValidator validator = getValidator(ano);
            if (needParams(ano)) {
                if (!validator.isValid(fieldValue, ano))
                    handleError(fieldName, fieldValue, ano);
            } else if (!validator.isValid(fieldValue))
                handleError(fieldName, fieldValue, ano);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * in case, that the field is collection, this method will be called to handle it
     *
     * @param fieldName  field name
     * @param fieldValue field value
     */
    private void handleCollection(String fieldName, Object fieldValue) throws IllegalAccessException {
        Annotation[] annotations = getCollAnnotations();
        int i = 0;
        for (Object collElement : (Collection) fieldValue) {
            checkField(fieldName + "[" + i + "]", collElement, annotations);
            i++;
        }
    }

    /**
     * method for handling an errors, when some field value doesn't meet his annotation conditions
     *
     * @param fieldName  field name
     * @param fieldValue field value
     * @param ano        the annotation, which his condition didn't meet
     */
    private void handleError(String fieldName, Object fieldValue, Annotation ano) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName(ano.annotationType().getName().replace(".annotation",
                "") + "Error");
        IMyErrorHandler IMyErrorHandler = (IMyErrorHandler) c.getDeclaredConstructor().newInstance();
        if (needParams(ano)) {
            validationErrorSet.add(IMyErrorHandler.createValidationError(fieldName, fieldValue, ano));
            return;
        }
        validationErrorSet.add(IMyErrorHandler.createValidationError(fieldName, fieldValue));
    }

    /**
     * @return get annotation from inside a collection at specific deep
     */
    private Annotation[] getCollAnnotations() {
        if (!stopDigging)
            annotatedType = ((AnnotatedParameterizedType) annotatedType).getAnnotatedActualTypeArguments()[0];
        return annotatedType.getAnnotations();
    }

    /**
     * @param fieldValue some object
     * @return true, if object is instance of List interface, false otherwise
     */
    private boolean isCollection(Object fieldValue) {
        return (fieldValue instanceof List);
    }

    /**
     * @param ano some annotation from validation.annotation package
     * @return for each given annotation will be created appropriate class for it from validation.validator
     */
    private IValidator getValidator(Annotation ano) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Class c = Class.forName(ano.annotationType().getName().replace(".annotation",
                "") + "Validator");
        return (IValidator) c.getDeclaredConstructor().newInstance();
    }

    /**
     * @param ano some annotation from validation.annotation package
     * @return true, if given annotation need parameters, false otherwise
     */
    private boolean needParams(Annotation ano) {
        return ((ano.annotationType()).equals(AnyOf.class) ||
                (ano.annotationType()).equals(InRange.class) ||
                (ano.annotationType()).equals(Size.class));
    }

    /**
     * @param object given object
     * @return true, if the object is annotated by Constrained annotation, false otherwise
     */
    private boolean isConstrained(Object object) {
        if (object == null)
            return false;
        Annotation[] objAnnotations = object.getClass().getAnnotations();
        return Arrays.stream(objAnnotations).anyMatch(x -> (x.annotationType()).equals(Constrained.class));
    }

    /**
     * @return the annotations of given field
     */
    private Annotation[] getAnnotations(Field f) {
        return f.getAnnotatedType().getAnnotations();
    }

    /**
     * @return the declared fields of given object
     */
    private Field[] getFields(Object object) {
        return object.getClass().getDeclaredFields();
    }
}
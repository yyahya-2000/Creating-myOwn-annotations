package main;

import main.annotation.InRange;

import java.lang.annotation.Annotation;

class InRangeValidator implements IValidator {

    /**
     * Method represent for checking the value of the filed, carrying annotation @InRange
     *
     * @param fieldValue the value of the field
     * @param ano        @InRange annotation
     * @return true, if the field value between or equal "min" and "max" of @InRange annotation, false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue, Annotation ano) {
        long min = ((InRange) ano).min();
        long max = ((InRange) ano).max();

        if (fieldValue instanceof Integer)
            return (((Integer) fieldValue) >= min && ((Integer) fieldValue) <= max);
        else if (fieldValue instanceof Byte)
            return (((Byte) fieldValue) >= min && ((Byte) fieldValue) <= max);
        else if (fieldValue instanceof Short)
            return (((Short) fieldValue) >= min && ((Short) fieldValue) <= max);
        else if (fieldValue instanceof Long)
            return (((Long) fieldValue) >= min && ((Long) fieldValue) <= max);

        return true;
    }
}

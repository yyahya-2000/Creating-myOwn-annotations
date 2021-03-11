package main;

import main.annotation.AnyOf;

import java.lang.annotation.Annotation;
import java.util.Arrays;

class AnyOfValidator implements IValidator {


    /**
     * Method represent for checking the value of the filed, carrying annotation @AnyOf
     *
     * @param fieldValue the value of the field
     * @param ano        @AnyOf annotation
     * @return true, if the field value is any of values inside the only annotation field(value) , false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue, Annotation ano) {
        if (fieldValue instanceof String) {
            String value = (String) fieldValue;
            return Arrays.asList(((AnyOf) ano).value()).contains(value);
        }

        return true;
    }
}

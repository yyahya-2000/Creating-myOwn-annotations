package main;

import main.annotation.Size;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

class SizeValidator implements IValidator {

    /**
     * Method represent for checking the value of the filed, carrying annotation @Size
     *
     * @param fieldValue the value of the field
     * @param ano        @Size annotation
     * @return true, if the field value size between or equal "min" and "max" of @Size annotation, false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue, Annotation ano) {
        int min = ((Size) ano).min();
        int max = ((Size) ano).max();
        int size = -1;

        if (fieldValue instanceof Collection)
            size = ((Collection) fieldValue).size();
        else if (fieldValue instanceof Map)
            size = ((Map) fieldValue).size();
        else if (fieldValue instanceof String)
            size = ((String) fieldValue).length();

        return size == -1 || (size <= max && size >= min);
    }
}

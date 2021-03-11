package main;

import java.lang.annotation.Annotation;

/**
 * Interface responsible for checking the value of the field, carrying annotation
 */
interface IValidator {
    /**
     * Method represent for checking the value of the filed, carrying annotation with parameters
     *
     * @param fieldValue the value of the field
     * @param ano        field's annotation
     * @return true, if the field value meet annotation conditions, false otherwise
     */
    default boolean isValid(Object fieldValue, Annotation ano) {
        return false;
    }

    /**
     * Method represent for checking the value of the filed, carrying annotation without parameters
     *
     * @param fieldValue the value of the field
     * @return true, if the field value meet annotation conditions, false otherwise
     */
    default boolean isValid(Object fieldValue) {
        return false;
    }
}

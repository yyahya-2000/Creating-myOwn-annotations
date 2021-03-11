package main;

import java.lang.annotation.Annotation;

/**
 * Interface responsible for creating ValidationError instance for each field doesn't meet
 * his annotation condition
 */
interface IMyErrorHandler {
    /**
     * Method responsible for creating ValidationError instance for each field doesn't
     * meet his annotation(without parameters) conditions
     *
     * @param fieldPath  the path where you can find the value that failed validation
     * @param fieldValue an object that did not pass validation
     * @return ValidationError instance
     */
     default ValidationError createValidationError(String fieldPath, Object fieldValue) {
        return null;
    }

    /**
     * Method responsible for creating ValidationError instance for each field doesn't
     * meet his annotation(with parameters) conditions
     *
     * @param fieldPath  the path where you can find the value that failed validation
     * @param fieldValue an object that did not pass validation
     * @param ano        field annotation
     * @return ValidationError instance
     */
    default ValidationError createValidationError(String fieldPath, Object fieldValue, Annotation ano) {
        return null;
    }
}

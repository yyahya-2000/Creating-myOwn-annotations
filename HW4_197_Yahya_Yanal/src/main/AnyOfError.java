package main;

import main.annotation.AnyOf;

import java.lang.annotation.Annotation;

class AnyOfError implements IMyErrorHandler {

    /**
     * Method responsible for creating ValidationError instance for each field doesn't
     * meet AnyOf annotation conditions
     *
     * @param fieldPath  the path where you can find the value that failed validation
     * @param fieldValue an object that did not pass validation
     * @param ano        field annotation
     * @return ValidationError instance
     */
    @Override
    public ValidationError createValidationError(String fieldPath, Object fieldValue, Annotation ano) {
        StringBuilder message = new StringBuilder("must be one of ");
        for (String st : ((AnyOf) ano).value())
            message.append("'").append(st).append("', ");
        if (message.length() > 1)
            message.setLength(message.length() - 2);

        return new ValidationErrorClass(message.toString(), fieldPath, fieldValue);
    }
}

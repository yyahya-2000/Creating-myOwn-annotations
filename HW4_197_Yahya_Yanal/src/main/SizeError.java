package main;

import main.annotation.Size;

import java.lang.annotation.Annotation;

class SizeError implements IMyErrorHandler {

    /**
     * Method responsible for creating ValidationError instance for each field doesn't
     * meet Size annotation conditions
     *
     * @param fieldPath  the path where you can find the value that failed validation
     * @param fieldValue an object that did not pass validation
     * @param ano        field annotation
     * @return ValidationError instance
     */
    @Override
    public ValidationError createValidationError(String fieldPath, Object fieldValue, Annotation ano) {
        StringBuilder message = new StringBuilder();
        int min = ((Size) ano).min();
        int max = ((Size) ano).max();
        if (min < max)
            message.append("the size must be in range between ").append(min).append(" and ").append(max);
        else if (min > max)
            message.append("the field has annotation with min: ").
                    append(min).append(" more than max: ").append(max).
                    append(" , so always will be a problem with the field size, whatever it's");
        else
            message.append("the size must be equal to ").append(min);

        return new ValidationErrorClass(message.toString(), fieldPath, fieldValue);
    }
}

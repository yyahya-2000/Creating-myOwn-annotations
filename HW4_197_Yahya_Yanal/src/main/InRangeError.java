package main;

import main.annotation.InRange;

import java.lang.annotation.Annotation;

class InRangeError implements IMyErrorHandler {

    /**
     * Method responsible for creating ValidationError instance for each field doesn't
     * meet InRange annotation conditions
     *
     * @param fieldPath  the path where you can find the value that failed validation
     * @param fieldValue an object that did not pass validation
     * @param ano        field annotation
     * @return ValidationError instance
     */
    @Override
    public ValidationError createValidationError(String fieldPath, Object fieldValue, Annotation ano) {
        StringBuilder message = new StringBuilder();
        long min = ((InRange) ano).min();
        long max = ((InRange) ano).max();
        if (min < max)
            message.append("must be in range between ").append(min).append(" and ").append(max);
        else if (min > max)
            message.append("the field has annotation with min: ").
                    append(min).append(" more than max: ").append(max).
                    append(" , so always will be a problem with the field value, whatever it's");
        else
            message.append("must be equal to ").append(min);
        return new ValidationErrorClass(message.toString(),/*path*/ fieldPath,/*failedValue*/ fieldValue);
    }
}

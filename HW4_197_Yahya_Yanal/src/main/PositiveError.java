package main;

class PositiveError implements IMyErrorHandler {

    /**
     * Method responsible for creating ValidationError instance for each field doesn't
     * meet Positive annotation conditions
     *
     * @param fieldPath  the path where you can find the value that failed validation
     * @param fieldValue an object that did not pass validation
     * @return ValidationError instance
     */
    @Override
    public ValidationError createValidationError(String fieldPath, Object fieldValue) {
        return new ValidationErrorClass("must be positive", fieldPath, fieldValue);
    }
}

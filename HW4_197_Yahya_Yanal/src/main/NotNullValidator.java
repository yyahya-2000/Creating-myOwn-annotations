package main;

class NotNullValidator implements IValidator {

    /**
     * Method represent for checking the value of the filed, carrying annotation @NotNull
     *
     * @param fieldValue the value of the field
     * @return true, if the field value isn't null, false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue) {
        return fieldValue != null;
    }
}

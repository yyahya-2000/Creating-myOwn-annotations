package main;

class NotBlankValidator implements IValidator {

    /**
     * Method represent for checking the value of the filed, carrying annotation @NotBlank
     *
     * @param fieldValue the value of the field
     * @return true, if the field value isn't blank, false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue) {
        String value = (String) fieldValue;

        return value == null || !(value).isBlank();
    }
}

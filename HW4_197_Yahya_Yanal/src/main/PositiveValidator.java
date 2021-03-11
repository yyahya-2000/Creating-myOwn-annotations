package main;

class PositiveValidator implements IValidator {

    /**
     * Method represent for checking the value of the filed, carrying annotation @Positive
     *
     * @param fieldValue the value of the field
     * @return true, if the field value is positive, false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue) {

        if (fieldValue instanceof Integer)
            return ((Integer) fieldValue) >= 0;
        else if (fieldValue instanceof Byte)
            return ((Byte) fieldValue) >= 0;
        else if (fieldValue instanceof Short)
            return ((Short) fieldValue) >= 0;
        else if (fieldValue instanceof Long)
            return ((Long) fieldValue) >= 0;

        return true;
    }
}
package main;

public class ValidationErrorClass implements ValidationError {
    private final String message;
    private final String path;
    private final Object failedValue;

    //constructor
    public ValidationErrorClass(String message, String path, Object failedValue) {
        this.message = message;
        this.path = path;
        this.failedValue = failedValue;
    }

    /**
     * @return returns error description
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return returns error description
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * @return returns the path where you can find the value that failed validation.
     */
    @Override
    public Object getFailedValue() {
        return failedValue;
    }
}

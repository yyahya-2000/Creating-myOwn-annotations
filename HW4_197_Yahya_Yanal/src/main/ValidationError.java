package main;

/**
 * The ValidationError interface is a validation error
 */
public interface ValidationError {
    /**
     * @return returns error description
     */
    String getMessage();

    /**
     * @return returns error description
     */
    String getPath();

    /**
     * @return returns the path where you can find the value that failed validation.
     */
    Object getFailedValue();

}

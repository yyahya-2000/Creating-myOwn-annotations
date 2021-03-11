package main;

import java.util.Set;

public interface Validator {
    /**
     * @param object the object, which his fields will be checked
     * @return returns Set of detected ValidationErrors
     */
    Set<ValidationError> validate(Object object);
}

package main;

import java.util.Collection;
import java.util.Map;

class NotEmptyValidator implements IValidator {

    /**
     * Method represent for checking the value of the filed, carrying annotation @NotEmpty
     *
     * @param fieldValue the value of the field
     * @return true, if the field value isn't empty, false otherwise
     */
    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue instanceof Collection)
            return !((Collection) fieldValue).isEmpty();
        else if (fieldValue instanceof Map)
            return !((Map) fieldValue).isEmpty();
        else if (fieldValue instanceof String)
            return !((String) fieldValue).isEmpty();

        return true;
    }
}

package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotBlankErrorTest {
    final String EXPECTED_MESSAGE = "must not be blank";
    final NotBlankError notBlankError = new NotBlankError();

    @Test
    void createValidationError_1() {
        String value = "    ";
        ValidationError actual = notBlankError.createValidationError("value", value);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("value", actual.getPath());
        assertEquals(value, actual.getFailedValue());

    }

    @Test
    void createValidationError_2() {
        String value = "";
        ValidationError actual = notBlankError.createValidationError("value", value);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("value", actual.getPath());
        assertEquals(value, actual.getFailedValue());
    }

}
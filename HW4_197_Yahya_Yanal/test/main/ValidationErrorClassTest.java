package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationErrorClassTest {
    final ValidationErrorClass validationErrorClass =
            new ValidationErrorClass("message", "path", "some value");

    @Test
    void getMessage() {
        assertEquals("message", validationErrorClass.getMessage());
    }

    @Test
    void getPath() {
        assertEquals("path", validationErrorClass.getPath());

    }

    @Test
    void getFailedValue() {
        assertEquals("some value", validationErrorClass.getFailedValue());

    }
}
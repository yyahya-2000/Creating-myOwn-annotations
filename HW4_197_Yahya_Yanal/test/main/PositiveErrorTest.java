package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveErrorTest {

    final String EXPECTED_MESSAGE = "must be positive";
    final PositiveError positiveError = new PositiveError();

    @Test
    void createValidationError_1() {
        int intValue = -1234;
        ValidationError actual = positiveError.createValidationError("intValue", intValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("intValue", actual.getPath());
        assertEquals(intValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_2() {
        short shortValue = -23;
        ValidationError actual = positiveError.createValidationError("shortValue", shortValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("shortValue", actual.getPath());
        assertEquals(shortValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_3() {
        byte byteValue = (byte) -255;
        ValidationError actual = positiveError.createValidationError("byteValue", byteValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("byteValue", actual.getPath());
        assertEquals(byteValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_4() {
        long longValue = -13145432L;
        ValidationError actual = positiveError.createValidationError("longValue", longValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("longValue", actual.getPath());
        assertEquals(longValue, actual.getFailedValue());
    }
}
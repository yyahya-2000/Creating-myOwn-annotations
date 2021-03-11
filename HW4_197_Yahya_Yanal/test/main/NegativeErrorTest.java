package main;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NegativeErrorTest {
    final String EXPECTED_MESSAGE = "must be negative";
    final NegativeError negativeError = new NegativeError();

    @Test
    void createValidationError_1() {
        int intValue = 1234;
        ValidationError actual = negativeError.createValidationError("intValue", intValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("intValue", actual.getPath());
        assertEquals(intValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_2() {
        short shortValue = 23;
        ValidationError actual = negativeError.createValidationError("shortValue", shortValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("shortValue", actual.getPath());
        assertEquals(shortValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_3() {
        byte byteValue = (byte) 255;
        ValidationError actual = negativeError.createValidationError("byteValue", byteValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("byteValue", actual.getPath());
        assertEquals(byteValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_4() {
        long longValue = 13145432L;
        ValidationError actual = negativeError.createValidationError("longValue", longValue);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("longValue", actual.getPath());
        assertEquals(longValue, actual.getFailedValue());
    }
}
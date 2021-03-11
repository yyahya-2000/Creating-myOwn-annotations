package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositiveValidatorTest {

    final PositiveValidator positiveValidator = new PositiveValidator();

    @Test
    void isValid_1() {
        assertTrue(positiveValidator.isValid(423));
    }

    @Test
    void isValid_2() {
        assertTrue(positiveValidator.isValid((short) 123));
    }

    @Test
    void isValid_3() {
        assertFalse(positiveValidator.isValid((byte) -23));
    }

    @Test
    void isValid_4() {
        assertFalse(positiveValidator.isValid(-234234234L));
    }
}
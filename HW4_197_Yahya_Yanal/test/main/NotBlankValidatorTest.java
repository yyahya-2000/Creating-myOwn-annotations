package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotBlankValidatorTest {

    final NotBlankValidator notBlankValidator = new NotBlankValidator();

    @Test
    void isValid_1() {
        assertFalse(notBlankValidator.isValid("   "));
    }

    @Test
    void isValid_2() {
        assertTrue(notBlankValidator.isValid(null));
    }

    @Test
    void isValid_3() {
        assertTrue(notBlankValidator.isValid("Hi"));
    }
}
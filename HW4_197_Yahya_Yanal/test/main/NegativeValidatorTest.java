package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.annotation.Negative;


import static org.junit.jupiter.api.Assertions.*;

class NegativeValidatorTest {
    @Negative
    static int intValue;
    @Negative
    static short shortValue;
    @Negative
    static byte byteValue;
    @Negative
    static long longValue;

    final NegativeValidator negativeValidator = new NegativeValidator();

    @BeforeAll
    static void setUp() {
        intValue = -10;
        shortValue = -3;
        byteValue = 124;
        longValue = 23456;
    }

    @Test
    void isValid_1() {
        assertTrue(negativeValidator.isValid(intValue));
    }

    @Test
    void isValid_2() {
        assertTrue(negativeValidator.isValid(shortValue));
    }

    @Test
    void isValid_3() {
        assertFalse(negativeValidator.isValid(byteValue));
    }

    @Test
    void isValid_4() {
        assertFalse(negativeValidator.isValid(longValue));
    }

}
package main;

import org.junit.jupiter.api.*;
import main.annotation.InRange;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InRangeValidatorTest {
    static InRangeValidatorTest inRangeValidatorTest = new InRangeValidatorTest();
    @InRange(min = 0, max = 10)
    static int intValue;
    @InRange(min = 3, max = 5)
    static short shortValue;
    @InRange(min = 0, max = 0)
    static byte byteValue;
    @InRange(min = 10, max = 1)
    static long longValue;
    final InRangeValidator inRangeValidator = new InRangeValidator();

    @BeforeAll
    static void setUp() {
        intValue = 5;
        shortValue = 3;
        byteValue = 10;
        longValue = -3;
    }

    @Test
    void isValid_1() {
        Field f = null;
        try {
            f = inRangeValidatorTest.getClass().getDeclaredField("intValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = inRangeValidator.isValid(intValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertTrue(res);
    }

    @Test
    void isValid_2() {
        Field f = null;
        try {
            f = inRangeValidatorTest.getClass().getDeclaredField("shortValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = inRangeValidator.isValid(shortValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertTrue(res);
    }

    @Test
    void isValid_3() {
        Field f = null;
        try {
            f = inRangeValidatorTest.getClass().getDeclaredField("byteValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = inRangeValidator.isValid(byteValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertFalse(res);
    }

    @Test
    void isValid_4() {
        Field f = null;
        try {
            f = inRangeValidatorTest.getClass().getDeclaredField("longValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = inRangeValidator.isValid(longValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertFalse(res);
    }
}
package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.annotation.InRange;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class InRangeErrorTest {

    static InRangeErrorTest inRangeErrorTest = new InRangeErrorTest();
    @InRange(min = 0, max = 10)
    static int intValue;
    @InRange(min = 3, max = 5)
    static short shortValue;
    @InRange(min = 0, max = 0)
    static byte byteValue;
    @InRange(min = 10, max = 1)
    static long longValue;
    final InRangeError inRangeError = new InRangeError();

    @BeforeAll
    static void setUp() {
        intValue = -1;
        shortValue = 2;
        byteValue = 10;
        longValue = 17;
    }

    @Test
    void createValidationError_1() {
        Field f = null;
        try {
            f = inRangeErrorTest.getClass().getDeclaredField("intValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = inRangeError.createValidationError("intValue", intValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertEquals("must be in range between 0 and 10", actual.getMessage());
        assertEquals(intValue, actual.getFailedValue());
        assertEquals("intValue", actual.getPath());
    }

    @Test
    void createValidationError_2() {
        Field f = null;
        try {
            f = inRangeErrorTest.getClass().getDeclaredField("shortValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = inRangeError.createValidationError("shortValue", shortValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertEquals("must be in range between 3 and 5", actual.getMessage());
        assertEquals(shortValue, actual.getFailedValue());
        assertEquals("shortValue", actual.getPath());
    }

    @Test
    void createValidationError_3() {
        Field f = null;
        try {
            f = inRangeErrorTest.getClass().getDeclaredField("byteValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = inRangeError.createValidationError("byteValue", byteValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertEquals("must be equal to 0", actual.getMessage());
        assertEquals(byteValue, actual.getFailedValue());
        assertEquals("byteValue", actual.getPath());
    }

    @Test
    void createValidationError_4() {
        Field f = null;
        try {
            f = inRangeErrorTest.getClass().getDeclaredField("longValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = inRangeError.createValidationError("longValue", longValue,
                f.getAnnotatedType().getAnnotation(InRange.class));
        assertEquals("the field has annotation with min: 10 more than max: 1 ," +
                        " so always will be a problem with the field value, whatever it's",
                actual.getMessage());
        assertEquals(longValue, actual.getFailedValue());
        assertEquals("longValue", actual.getPath());
    }
}
package main;

import org.junit.jupiter.api.*;
import main.annotation.AnyOf;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


class AnyOfValidatorTest {
    static AnyOfValidatorTest anyOfValidatorTest = new AnyOfValidatorTest();
    @AnyOf({"English", "French", "Arabic"})
    String value;
    final AnyOfValidator anyOfValidator = new AnyOfValidator();
    static int i = 0;
    static String[] values;

    @BeforeAll
    static void setUp() {
        values = new String[]{"Chinese", "Spanish", "English", "Hindi"};
    }

    @BeforeEach
    void tearDown() {
        value = values[i++];
    }

    private boolean callIsValid() {
        Field f = null;
        try {
            f = anyOfValidatorTest.getClass().getDeclaredField("value");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        return anyOfValidator.isValid(value,
                f.getAnnotatedType().getAnnotation(AnyOf.class));
    }

    @Test
    @Order(1)
    void isValid_1() {
        boolean res = callIsValid();
        assertFalse(res);
    }

    @Test
    @Order(2)
    void isValid_2() {
        boolean res = callIsValid();
        assertFalse(res);
    }

    @Test
    @Order(3)
    void isValid_3() {
        boolean res = callIsValid();
        assertTrue(res);
    }

    @Test
    @Order(4)
    void isValid_4() {
        boolean res = callIsValid();
        assertFalse(res);
    }
}
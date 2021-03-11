package main;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import main.annotation.AnyOf;

import java.lang.reflect.Field;


class AnyOfErrorTest {

    static AnyOfErrorTest anyOfErrorTest = new AnyOfErrorTest();
    @AnyOf({"English", "French", "Arabic"})
    String value;
    final AnyOfError anyOfError = new AnyOfError();
    static int i = 0;
    static String[] values;
    final String EXPECTED_MESSAGE = "must be one of 'English', 'French', 'Arabic'";

    @BeforeAll
    static void setUp() {
        values = new String[]{"Chinese", "Spanish", "Vietnamese", "Hindi"};
    }

    @BeforeEach
    void tearDown() {
        value = values[i++];
    }

    private ValidationError callCreateValidationError() {
        Field f = null;
        try {
            f = anyOfErrorTest.getClass().getDeclaredField("value");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        return anyOfError.createValidationError("value", value,
                f.getAnnotatedType().getAnnotation(AnyOf.class));
    }

    @Test
    @Order(1)
    void createValidationError_1() {
        ValidationError res = callCreateValidationError();
        assertEquals(res.getFailedValue(), value);
        assertEquals(res.getMessage(), EXPECTED_MESSAGE);
        assertEquals(res.getPath(), "value");
    }

    @Test
    @Order(2)
    void createValidationError_2() {
        ValidationError res = callCreateValidationError();
        assertEquals(value, res.getFailedValue());
        assertEquals(EXPECTED_MESSAGE, res.getMessage());
        assertEquals("value", res.getPath());
    }

    @Test
    @Order(3)
    void createValidationError_3() {
        ValidationError res = callCreateValidationError();
        assertEquals(value, res.getFailedValue());
        assertEquals(EXPECTED_MESSAGE, res.getMessage());
        assertEquals("value", res.getPath());
    }

    @Test
    @Order(4)
    void createValidationError_4() {
        ValidationError res = callCreateValidationError();
        assertEquals(value, res.getFailedValue());
        assertEquals(EXPECTED_MESSAGE, res.getMessage());
        assertEquals("value", res.getPath());
    }
}
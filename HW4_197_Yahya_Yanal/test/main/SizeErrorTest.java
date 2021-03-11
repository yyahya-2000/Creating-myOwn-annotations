package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.annotation.Size;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SizeErrorTest {
    static SizeErrorTest sizeErrorTest = new SizeErrorTest();
    @Size(min = 5, max = 10)
    static String stringValue;
    @Size(min = 3, max = 5)
    static final Map<Integer, Integer> mapValue = new HashMap<>();
    @Size(min = 3, max = 3)
    static final List<String> listValue = new ArrayList<>();
    @Size(min = 5, max = -1)
    static final Set<Long> setValue = new HashSet<>();

    final SizeError sizeError = new SizeError();

    @BeforeAll
    static void setUp() {
        stringValue = "Hi";
        mapValue.put(1, 1);
        mapValue.put(2, 2);
        setValue.add(12L);
        setValue.add(124L);
        setValue.add(23L);
        setValue.add(312L);
        listValue.add("Hello!");
    }

    @Test
    void createValidationError_1() {
        Field f = null;
        try {
            f = sizeErrorTest.getClass().getDeclaredField("stringValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = sizeError.createValidationError("stringValue", stringValue,
                f.getAnnotatedType().getAnnotation(Size.class));
        assertEquals("the size must be in range between 5 and 10", actual.getMessage());
        assertEquals("stringValue", actual.getPath());
        assertEquals(stringValue, actual.getFailedValue());

    }

    @Test
    void createValidationError_2() {
        Field f = null;
        try {
            f = sizeErrorTest.getClass().getDeclaredField("listValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = sizeError.createValidationError("listValue", listValue, f.getAnnotatedType().getAnnotation(Size.class));
        assertEquals("the size must be equal to 3", actual.getMessage());
        assertEquals("listValue", actual.getPath());
        assertEquals(listValue, actual.getFailedValue());
    }


    @Test
    void createValidationError_3() {
        Field f = null;
        try {
            f = sizeErrorTest.getClass().getDeclaredField("mapValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = sizeError.createValidationError("mapValue", mapValue, f.getAnnotatedType().getAnnotation(Size.class));
        assertEquals("the size must be in range between 3 and 5", actual.getMessage());
        assertEquals("mapValue", actual.getPath());
        assertEquals(mapValue, actual.getFailedValue());
    }

    @Test
    void createValidationError_4() {
        Field f = null;
        try {
            f = sizeErrorTest.getClass().getDeclaredField("setValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        ValidationError actual = sizeError.createValidationError("setValue", setValue, f.getAnnotatedType().getAnnotation(Size.class));
        assertEquals("the field has annotation with min: 5 more than max: -1 ," +
                        " so always will be a problem with the field size, whatever it's",
                actual.getMessage());
        assertEquals("setValue", actual.getPath());
        assertEquals(setValue, actual.getFailedValue());
    }
}
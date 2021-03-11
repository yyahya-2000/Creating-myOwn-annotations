package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.annotation.Size;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SizeValidatorTest {

    static SizeValidatorTest sizeValidatorTest = new SizeValidatorTest();
    @Size(min = 0, max = 10)
    static String stringValue1;
    @Size(min = 0, max = 10)
    static String stringValue2;
    @Size(min = 3, max = 5)
    static Map<Integer, Integer> mapValue = new HashMap<>();
    @Size(min = 0, max = 0)
    static List<String> listValue = new ArrayList<>();
    @Size(min = 10, max = 15)
    static Set<Long> setValue = new HashSet<>();

    static SizeValidator sizeValidator;

    @BeforeAll
    static void setUp() {
        sizeValidator = new SizeValidator();
        stringValue2 = "";
        mapValue.put(1, 1);
        mapValue.put(2, 2);
        mapValue.put(3, 3);
        setValue.add(12L);
        setValue.add(124L);
        setValue.add(23L);
        setValue.add(312L);
        listValue.add("Hello!");
    }

    @Test
    void isValid_1() {
        Field f = null;
        try {
            f = sizeValidatorTest.getClass().getDeclaredField("stringValue1");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = sizeValidator.isValid(stringValue1,
                f.getAnnotatedType().getAnnotation(Size.class));
        assertTrue(res);
    }

    @Test
    void isValid_2() {
        Field f = null;
        try {
            f = sizeValidatorTest.getClass().getDeclaredField("stringValue2");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = sizeValidator.isValid(stringValue2,
                f.getAnnotatedType().getAnnotation(Size.class));
        assertTrue(res);
    }


    @Test
    void isValid_3() {
        Field f = null;
        try {
            f = sizeValidatorTest.getClass().getDeclaredField("mapValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = sizeValidator.isValid(mapValue,
                f.getAnnotatedType().getAnnotation(Size.class));
        assertTrue(res);
    }

    @Test
    void isValid_4() {
        Field f = null;
        try {
            f = sizeValidatorTest.getClass().getDeclaredField("listValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = sizeValidator.isValid(listValue,
                f.getAnnotatedType().getAnnotation(Size.class));
        assertFalse(res);
    }

    @Test
    void isValid_5() {
        Field f = null;
        try {
            f = sizeValidatorTest.getClass().getDeclaredField("setValue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert f != null;
        boolean res = sizeValidator.isValid(setValue,
                f.getAnnotatedType().getAnnotation(Size.class));
        assertFalse(res);
    }
}
package main;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotNullValidatorTest {

    final NotNullValidator notNullValidator = new NotNullValidator();

    @Test
    void isValid_1() {
        Set<Integer> set = null;
        assertFalse(notNullValidator.isValid(set));
    }

    @Test
    void isValid_2() {
        Set<String> hashSet = new HashSet<>();
        assertTrue(notNullValidator.isValid(hashSet));
    }

    @Test
    void isValid_3() {
        String s = null;
        assertFalse(notNullValidator.isValid(s));
    }

    @Test
    void isValid_4() {
        assertTrue(notNullValidator.isValid(""));
    }

    @Test
    void isValid_5() {
        Map<Integer, Integer> map = null;
        assertFalse(notNullValidator.isValid(map));
    }
}
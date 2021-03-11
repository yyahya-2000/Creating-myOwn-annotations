package main;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NotEmptyValidatorTest {

    final NotEmptyValidator notEmptyValidator = new NotEmptyValidator();

    @Test
    void isValid_1() {
        assertFalse(notEmptyValidator.isValid(new ArrayList<>()));
    }

    @Test
    void isValid_2() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Hi");
        assertTrue(notEmptyValidator.isValid(hashSet));
    }

    @Test
    void isValid_3() {
        assertFalse(notEmptyValidator.isValid(""));
    }

    @Test
    void isValid_4() {
        assertTrue(notEmptyValidator.isValid(null));
    }

    @Test
    void isValid_5() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        assertTrue(notEmptyValidator.isValid(map));
    }
}
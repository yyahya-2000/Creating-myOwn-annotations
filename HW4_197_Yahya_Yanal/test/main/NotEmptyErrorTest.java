package main;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyErrorTest {
    final String EXPECTED_MESSAGE = "must not be empty";
    final NotEmptyError notEmptyError = new NotEmptyError();

    @Test
    void createValidationError_1() {
        List<String> list = new ArrayList<>();
        ValidationError actual = notEmptyError.createValidationError("list", list);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("list", actual.getPath());
        assertEquals(list, actual.getFailedValue());

    }

    @Test
    void createValidationError_2() {
        Set<String> hashSet = new HashSet<>();
        ValidationError actual = notEmptyError.createValidationError("hashSet", hashSet);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("hashSet", actual.getPath());
        assertEquals(hashSet, actual.getFailedValue());
    }

    @Test
    void createValidationError_3() {
        String string = "";
        ValidationError actual = notEmptyError.createValidationError("string", string);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("string", actual.getPath());
        assertEquals(string, actual.getFailedValue());
    }

    @Test
    void createValidationError_4() {
        Map<Integer, Integer> map = new HashMap<>();
        ValidationError actual = notEmptyError.createValidationError("map", map);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("map", actual.getPath());
        assertEquals(map, actual.getFailedValue());
    }
}
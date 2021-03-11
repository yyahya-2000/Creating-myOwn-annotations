package main;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotNullErrorTest {
    final String EXPECTED_MESSAGE = "must not be null";
    final NotNullError notNullError = new NotNullError();

    @Test
    void createValidationError_1() {
        Set<Integer> set = null;
        ValidationError actual = notNullError.createValidationError("set", set);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("set", actual.getPath());
        assertEquals(set, actual.getFailedValue());
    }

    @Test
    void createValidationError_2() {
        String string = null;
        ValidationError actual = notNullError.createValidationError("string", string);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("string", actual.getPath());
        assertEquals(string, actual.getFailedValue());
    }

    @Test
    void createValidationError_3() {
        List<String> list = null;
        ValidationError actual = notNullError.createValidationError("list", list);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("list", actual.getPath());
        assertEquals(list, actual.getFailedValue());
    }

    @Test
    void createValidationError_4() {
        Map<Integer, Integer> map = null;
        ValidationError actual = notNullError.createValidationError("map", map);
        assertEquals(EXPECTED_MESSAGE, actual.getMessage());
        assertEquals("map", actual.getPath());
        assertEquals(map, actual.getFailedValue());
    }
}
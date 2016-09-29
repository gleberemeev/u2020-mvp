package ru.ltst.u2020mvp.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsTest {
    @Test
    public void isBlank() throws Exception {
        assertFalse(Strings.INSTANCE.isBlank("123"));
        assertTrue(Strings.INSTANCE.isBlank(" "));
        assertTrue(Strings.INSTANCE.isBlank(""));
        assertTrue(Strings.INSTANCE.isBlank(null));
    }

    @Test
    public void valueOrDefault() throws Exception {
        String defaultVal = "default";
        assertEquals(defaultVal, Strings.INSTANCE.valueOrDefault(" ", defaultVal));
        assertEquals(defaultVal, Strings.INSTANCE.valueOrDefault("", defaultVal));
        assertNotEquals(defaultVal, Strings.INSTANCE.valueOrDefault(",", defaultVal));
        assertEquals(defaultVal, Strings.INSTANCE.valueOrDefault(null, defaultVal));
    }

    @Test
    public void truncateAt() throws Exception {
        assertEquals("", Strings.INSTANCE.truncateAt("abc", 0));
        assertEquals("abc", Strings.INSTANCE.truncateAt("abcdef", 3));
        assertEquals("abc", Strings.INSTANCE.truncateAt("abc", 6));
        assertEquals("", Strings.INSTANCE.truncateAt("", 4));
        assertNull(Strings.INSTANCE.truncateAt(null, 2));
        assertEquals("abc", Strings.INSTANCE.truncateAt("abc", -1));
        assertNull(Strings.INSTANCE.truncateAt(null, -1));
    }

}
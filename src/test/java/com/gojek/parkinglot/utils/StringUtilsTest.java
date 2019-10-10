package com.gojek.parkinglot.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void isBlank() throws Exception {
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank(null));
        assertFalse(StringUtils.isBlank("test"));
    }

    @Test
    public void isNotBlank() throws Exception {
        assertFalse(StringUtils.isNotBlank(""));
        assertFalse(StringUtils.isNotBlank(null));
        assertTrue(StringUtils.isNotBlank("test"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void validateNotBlank() throws Exception {
        StringUtils.validateNotBlank("");
        StringUtils.validateNotBlank(null);
    }

}
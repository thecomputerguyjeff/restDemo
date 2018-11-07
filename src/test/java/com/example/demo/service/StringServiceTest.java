package com.example.demo.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringServiceTest {

    private StringService stringService;

    @Before
    public void setup() {
        stringService = new StringService(restTemplate);
    }

    @Test
    public void upperCase_shouldReturnUpperCaseString_whenPassedUpperCaseString() {
        String expected = "HELLO";

        String actual = stringService.upperCase("HELLO");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void upperCase_shouldReturnUpperCaseString_whenPassedLowerCaseString() throws Exception {
        String expected = "HELLO";
        String actual = stringService.upperCase("hello");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void upperCase_shouldReturnNull_whenPassedNull() throws Exception {
        String actual = stringService.upperCase(null);
        assertThat(actual).isNull();

    }

}
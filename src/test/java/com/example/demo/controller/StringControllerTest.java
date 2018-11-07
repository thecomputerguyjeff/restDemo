package com.example.demo.controller;

import com.example.demo.model.InputObject;
import com.example.demo.model.OutputObject;
import com.example.demo.service.SecurityService;
import com.example.demo.service.StringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StringControllerTest {

    @Mock
    private StringService stringService;

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private StringController stringController;

    @Test
    public void getUpperCaseString_returnsValidOutputObject() throws Exception {
        when(stringService.upperCase(anyString())).thenReturn("TESTSTRING");
        InputObject inputObject = InputObject.builder()
                .string("teststring").build();

        OutputObject expected = OutputObject.builder()
                .string("TESTSTRING")
                .timestamp(LocalDateTime.now())
                .build();

        OutputObject actual = stringController.getUpperCaseString(inputObject, null);


        verify(stringService).upperCase(inputObject.getString());

        assertThat(actual).isInstanceOf(OutputObject.class);

        assertThat(actual.getString()).isEqualTo(expected.getString());

        assertThat(actual.getTimestamp()).isInstanceOf(LocalDateTime.class);

        assertThat(actual.getTimestamp()).isNotNull();
    }

    @Test
    public void getUpperCaseString_CallsSecurity() throws Exception {
        InputObject inputObject = InputObject.builder()
                .string("testString")
                .build();

        String key = "key";

        stringController.getUpperCaseString(inputObject, key);
        verify(securityService).authenticate(key);
    }

    @Test
    public void getUpperCaseString_CallsMocksInOrder() throws Exception {
        InputObject inputObject = InputObject.builder()
                .string("testString")
                .build();

        String key = "key";

        stringController.getUpperCaseString(inputObject, null);

        InOrder inOrder = inOrder(stringService, securityService);
        inOrder.verify(securityService).authenticate(key);
        inOrder.verify(stringService).upperCase("testString");
        inOrder.verifyNoMoreInteractions();
    }


}
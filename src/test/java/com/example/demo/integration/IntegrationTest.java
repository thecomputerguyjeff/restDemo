package com.example.demo.integration;

import com.example.demo.DemoApplication;
import com.example.demo.model.InputObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class IntegrationTest {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext webAppContext;

    @Test
    public void integrationTest() throws Exception {

        mvc = webAppContextSetup(webAppContext).build();

        InputObject input = InputObject.builder().string("testString").build();
        ObjectMapper mapper = new ObjectMapper();


        mvc.perform(post("/api/upperCase")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(input))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType
                        (MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.string", is("TESTSTRING")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));


    }


}

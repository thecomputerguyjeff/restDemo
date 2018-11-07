package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StringService {

    private RestTemplate restTemplate;

    public StringService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String upperCase(String inputString) {
        System.out.println("INTO THE SERVICE");
        return inputString == null ? null : inputString.toUpperCase();
    }

    public String getFact(Integer number) {

        return restTemplate.exchange("http://numbersapi.com/"+number, HttpMethod.GET, HttpEntity.EMPTY, String.class).getBody();
    }
}

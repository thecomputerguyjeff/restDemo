package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SquareService {

    public Integer getSquare(Integer number) {
        return number * number;
    }

}

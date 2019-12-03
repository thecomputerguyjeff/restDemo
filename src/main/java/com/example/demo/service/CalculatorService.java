package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {


    public double add(double x, double y) {
        return x + y;
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public double multiply(double x, double y) {
        return x * y;
    }

    public double addAll(List<Double> l) {
        return l.stream()
                .reduce((x, a) -> a += x)
                .orElse(0.0);
    }
}

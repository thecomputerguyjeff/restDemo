package com.example.demo.controller;

import com.example.demo.model.InputObject;
import com.example.demo.model.OutputObject;
import com.example.demo.service.SecurityService;
import com.example.demo.service.SquareService;
import com.example.demo.service.StringService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class StringController {

    private StringService stringService;
    private SecurityService securityService;
    private SquareService squareService;

    public StringController(StringService stringService, SecurityService securityService, SquareService squareService) {
        this.stringService = stringService;
        this.securityService = securityService;
        this.squareService = squareService;
    }

    @PostMapping("/upperCase")
    public OutputObject getUpperCaseString(@RequestBody InputObject inputObject, String key) {
        securityService.authenticate(key);

        System.out.println("BEFORE THE CALL");
        String upperCaseString = stringService.upperCase(inputObject.getString());
        System.out.println("AFTER THE CALL");


        return OutputObject.builder()
                .string(upperCaseString)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/square")
    public OutputObject getSquare(@RequestParam Integer number) {

        return OutputObject.builder()
                .string(stringService.getFact(number))
                .timestamp(LocalDateTime.now())
                .build();
    }
}

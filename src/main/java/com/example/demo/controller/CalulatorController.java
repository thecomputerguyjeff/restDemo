package com.example.demo.controller;

import com.example.demo.model.SubtractBody;
import com.example.demo.service.CalculatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api("Calculator Controller")
@RequestMapping("/api")
public class CalulatorController {

    private final CalculatorService calculatorService;

    public CalulatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @ApiResponses( value = {
            @ApiResponse(code = 202, message = "NOT A GOOD CODE"),
            @ApiResponse(code = 7000, message = "BLAH")

    })
    @GetMapping("/add/{first}/{second}")
    public double add(@ApiParam(required = false) @PathVariable(value = "first") double x, @PathVariable(value = "second") double y) {
        return calculatorService.add(x,y);
    }

    @PostMapping("/add")
    public double addAll(@RequestBody List<Double> list) {
        return calculatorService.addAll(list);
    }

    @PostMapping("/subtract")
    public double subtract(@RequestBody SubtractBody subtract) {
        return calculatorService.subtract(subtract.getX(), subtract.getY());
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam("first") double x, @RequestParam("second") Double y) {
        return calculatorService.multiply(x,y);
    }


}

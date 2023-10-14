package com.alexisakov.project.controllers;

import com.alexisakov.project.service.abstracts.FrequencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class FrequencyRestController {

    private final FrequencyService frequencyService;

    public FrequencyRestController(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    @Operation(summary = "Подсчет повторяющихся символов последовательности", tags = "Calculate frequency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/calculate-frequency")
    public ResponseEntity<Map<Character, Integer>> calculateFrequency(@RequestParam("key") @NotEmpty String value) {
        return new ResponseEntity<>(frequencyService.calculateFrequency(value), HttpStatus.OK);
    }

    @Operation(summary = "Подсчет повторяющихся символов последовательности, результат отсортирован по возрастанию", tags = "Calculate frequency ascending")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/calculate-frequency-asc")
    public ResponseEntity<Map<Character, Integer>> calculateFrequencyAscending(@RequestParam("key") @NotEmpty  String value) {
        return new ResponseEntity<>(frequencyService.calculateFrequencyAscending(value), HttpStatus.OK);
    }

    @Operation(summary = "Подсчет повторяющихся символов последовательности, результат отсортирован по убыванию", tags = "Calculate frequency descending")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/calculate-frequency-desc")
    public ResponseEntity<Map<Character, Integer>> calculateFrequencyDescending(@RequestParam("key") @NotEmpty String value) {
        return new ResponseEntity<>(frequencyService.calculateFrequencyDescending(value), HttpStatus.OK);
    }
}

package com.alexisakov.project;

import com.alexisakov.project.controllers.FrequencyRestController;
import com.alexisakov.project.service.abstracts.FrequencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureMockMvc
public class FrequencyRestControllerTests {
    private FrequencyRestController frequencyRestController;
    @Mock
    private FrequencyService frequencyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        frequencyRestController = new FrequencyRestController(frequencyService);
    }

    @Test
    public void testCalculateFrequency() {
        String value = "aaaaabcccc";
        Map<Character, Integer> expectedFrequency = new HashMap<>();
        expectedFrequency.put('a', 5);
        expectedFrequency.put('c', 4);
        expectedFrequency.put('b', 1);

        when(frequencyService.calculateFrequency(value)).thenReturn(expectedFrequency);

        ResponseEntity<Map<Character, Integer>> responseEntity =
                frequencyRestController.calculateFrequency(value);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFrequency, responseEntity.getBody());
    }

    @Test
    public void testCalculateFrequencyAscending() {
        String value = "aaaaabcccc";
        Map<Character, Integer> expectedFrequency = new LinkedHashMap<>();
        expectedFrequency.put('b', 1);
        expectedFrequency.put('c', 4);
        expectedFrequency.put('a', 5);

        when(frequencyService.calculateFrequencyAscending(value)).thenReturn(expectedFrequency);

        ResponseEntity<Map<Character, Integer>> responseEntity =
                frequencyRestController.calculateFrequencyAscending(value);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<Character, Integer> actualFrequency = responseEntity.getBody();

        assert actualFrequency != null;
        assertTrue(isAscSortedByValue(actualFrequency));

        assertEquals(expectedFrequency, actualFrequency);
    }

    @Test
    public void testCalculateFrequencyDescending() {
        String value = "aaaaabcccc";
        Map<Character, Integer> expectedFrequency = new LinkedHashMap<>();
        expectedFrequency.put('a', 5);
        expectedFrequency.put('c', 4);
        expectedFrequency.put('b', 1);

        when(frequencyService.calculateFrequencyDescending(value)).thenReturn(expectedFrequency);

        ResponseEntity<Map<Character, Integer>> responseEntity =
                frequencyRestController.calculateFrequencyDescending(value);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<Character, Integer> actualFrequency = responseEntity.getBody();

        assert actualFrequency != null;
        assertTrue(isDescSortedByValue(actualFrequency));

        assertEquals(expectedFrequency, actualFrequency);
    }

    private boolean isAscSortedByValue(Map<Character, Integer> map) {
        int prevValue = Integer.MIN_VALUE;
        for (int value : map.values()) {
            if (value < prevValue) {
                return false;
            }
            prevValue = value;
        }
        return true;
    }

    private boolean isDescSortedByValue(Map<Character, Integer> map) {
        int prevValue = Integer.MAX_VALUE;
        for (int value : map.values()) {
            if (value > prevValue) {
                return false;
            }
            prevValue = value;
        }
        return true;
    }
}

package com.alexisakov.project.service.abstracts;

import java.util.Map;

public interface FrequencyService {
    Map<Character, Integer> calculateFrequency(String value);
    Map<Character, Integer> calculateFrequencyDescending(String value);
    Map<Character, Integer> calculateFrequencyAscending(String value);
}

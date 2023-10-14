package com.alexisakov.project.service.implementation;

import com.alexisakov.project.service.abstracts.FrequencyService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FrequencyServiceImpl implements FrequencyService {
    @Override
    public Map<Character, Integer> calculateFrequency(String value) {
        Map<Character, Integer> map = new HashMap<>();

        for (Character c: value.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    @Override
    public Map<Character, Integer> calculateFrequencyDescending(String value) {
        Map<Character, Integer> frequencyMap = calculateFrequency(value);

        return frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<Character, Integer> calculateFrequencyAscending(String value) {
        Map<Character, Integer> frequencyMap = calculateFrequency(value);

        return frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
}

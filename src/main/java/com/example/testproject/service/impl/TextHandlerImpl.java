package com.example.testproject.service.impl;

import com.example.testproject.service.TextHandler;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Log4j
@Service
public class TextHandlerImpl implements TextHandler {

    private static final String regex = "\\s+";
    private static final String regex2 = "^[^\\p{IsLetter}0-9]+|[^\\p{IsLetter}0-9]+$";

    @Override
    public int checkingWordCount(String text) {

        log.debug("Method \"checkingWordCount()\" received the text: \"" + text + "\"");
        int count = text.split(regex).length;
        log.debug("Method \"checkingWordCount()\" return count: \"" + count + "\"");

        return count;
    }

    @Override
    public List<String> findFiveWordsAndSort(String text) {
        log.debug("Method \"findFiveWordsAndSort()\" received the text: \"" + text + "\"");
        String[] words = text.split(regex);
        log.debug("Get an array by dividing by space: \"" + Arrays.toString(words) + "\"");

        Map<String, Integer> repeatOfWords = new HashMap<>();

        for (String word : words) {
            String cleanedWord = word.replaceAll(regex2, "");
            repeatOfWords.merge(cleanedWord, 1, Integer::sum);
        }

        log.debug("Received a HashMap with the number of repetitions of words: \"" + repeatOfWords + "\"");

        List<String> mostFrequentWords = repeatOfWords.entrySet().stream()
                .sorted((x, y) -> x.getValue() == y.getValue() ? x.getKey().compareTo(y.getKey()) : y.getValue() - x.getValue())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        log.debug("Got the final result of the method \"findFiveWordsAndSort()\" without limit: \"" + mostFrequentWords + "\"");

        return mostFrequentWords;
    }
}

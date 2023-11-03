package com.example.testproject.service;

import com.example.testproject.service.impl.TextHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextHandlerImplTest {

    private TextHandler textHandler;

    @BeforeEach
    public void setup() {
        textHandler = new TextHandlerImpl();
    }

    @Test
    @DisplayName("The method should return the length of the string")
    void checkingWordCount() {
        String textToCheck = "A! fast brown, fox? with mail brown@mail.ru jumps- over...ленивую-собаку";

        int wordCount = textHandler.checkingWordCount(textToCheck);

        assertEquals(12, wordCount);
    }

    @Test
    @DisplayName("The method should find five frequently occurring words and sort them in reverse alphabetical order")
    void findFiveWordsAndSort() {
        String textToCheck = "A fast brown, fox with mail brown@mail.ru jumps over ленивую собаку";
        List<String> expectedTopFiveWords = Arrays.asList("with", "fox", "fast", "brown", "A");

        List<String> topFiveWords = textHandler.findFiveWordsAndSort(textToCheck);

        assertEquals(expectedTopFiveWords, topFiveWords);
    }
}
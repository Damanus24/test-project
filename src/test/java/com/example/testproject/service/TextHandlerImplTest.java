package com.example.testproject.service;

import com.example.testproject.service.impl.TextHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
class TextHandlerImplTest {

//    @Mock
    private TextHandler textHandler;

//    @InjectMocks
//    private TextHandlerImpl textHandlerImpl; //заменить в методах на textHandlerImpl

    @BeforeEach
    public void setup() {
        textHandler = new TextHandlerImpl();
    }

    @Test
    @DisplayName("The method should return the length of the string")
    void checkingWordCount() {
        String textToCheck = "A! fast brown, fox? with mail brown@mail.ru jumps- over... ленивую-собаку";

        int wordCount = textHandler.checkingWordCount(textToCheck);

        assertEquals(10, wordCount);
    }

    @Test
    @DisplayName("The method should find five frequently occurring words and sort them in reverse alphabetical order")
    void findFiveWordsAndSort() {
        String textToCheck = "A fast brown, fox with mail brown@mail.ru jumps over ленивую собаку";
        List<String> expectedTopFiveWords = Arrays.asList("A", "brown", "brown@mail.ru", "fast", "fox");

        List<String> topFiveWords = textHandler.findFiveWordsAndSort(textToCheck);

        assertEquals(expectedTopFiveWords, topFiveWords);
    }
}
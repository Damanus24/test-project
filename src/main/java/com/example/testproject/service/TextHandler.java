package com.example.testproject.service;

import java.util.List;

public interface TextHandler {
    public int checkingWordCount(String text);

    public List<String> findFiveWordsAndSort(String text);
}

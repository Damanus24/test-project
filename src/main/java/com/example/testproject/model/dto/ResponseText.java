package com.example.testproject.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResponseText {

    private int wordsCount;

    private List<String> mostFrequentWords;
}

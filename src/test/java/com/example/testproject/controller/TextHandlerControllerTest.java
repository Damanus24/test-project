package com.example.testproject.controller;

import com.example.testproject.model.dto.RequestText;
import com.example.testproject.model.dto.ResponseText;
import com.example.testproject.service.TextHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(TextHandlerController.class)
public class TextHandlerControllerTest {

    @Autowired
    private TextHandlerController textHandlerController;

    @MockBean
    private TextHandler textHandler;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(textHandlerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Method testCheck() should produce correct data with status 200")
    public void testCheck() throws Exception {

        String inputText = "This is a sample text for testing word count.";
        RequestText request = new RequestText(inputText);
        ResponseText expectedResponse = new ResponseText(8, Arrays.asList("a", "count", "for", "is", "sample"));

        Mockito.when(textHandler.checkingWordCount(request.getText())).thenReturn(expectedResponse.getWordsCount());
        Mockito.when(textHandler.findFiveWordsAndSort(request.getText())).thenReturn(expectedResponse.getMostFrequentWords());


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/wordstat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        ResponseText actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseText.class);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @DisplayName("Method testInvalidRequest() should handle invalid input")
    @NullAndEmptySource

    public void testInvalidRequest(String inputText) throws Exception {
        RequestText request = new RequestText(inputText);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/wordstat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();


        assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException);
    }
}
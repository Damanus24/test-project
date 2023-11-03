package com.example.testproject.controller;

import com.example.testproject.model.dto.ResponseText;
import com.example.testproject.model.dto.RequestText;
import com.example.testproject.service.TextHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(info =
@Info(
        title = "Text processing and analysis",
        version = "0.0.1",
        description = "Determining the number of words in text in UTF-8 encoding. " +
                "Also definition of five frequently occurring words in the text, sorted in descending order."))
public class TextHandlerController {

    private final TextHandler texthandler;

    @Autowired
    public TextHandlerController(TextHandler texthandler) {
        this.texthandler = texthandler;
    }


    @Operation(summary = "Text processing and analysis",
            responses = {
                    @ApiResponse(description = "Processing and analysis completed ", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RequestText.class))),
                    @ApiResponse(
                            description = "Not valid text ", responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MethodArgumentNotValidException.class)
                            ))})
    @PostMapping("/wordstat")
    public ResponseEntity<ResponseText> check(
            @Parameter(description = "The text that needs to be processing and analysis",
            schema = @Schema(implementation = RequestText.class))
            @Valid @RequestBody RequestText request) {

        return ResponseEntity.ok(new ResponseText(texthandler.checkingWordCount(String.valueOf(request)),
                texthandler.findFiveWordsAndSort(String.valueOf(request))));
    }

}

package com.example.testproject.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestText {

    @NotBlank(message = "Это поле не должно быть пустым")
    @NotNull(message = "Это поле не должно быть null")
    private String text;

    @Override
    public String toString() {
        return text;
    }
}

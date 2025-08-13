package com.jesperweijnen.trivia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenTdbResponseDto {
    @JsonProperty("response_code")
    private int responseCode;
    private List<OpenTdbQuestionDto> results;
}

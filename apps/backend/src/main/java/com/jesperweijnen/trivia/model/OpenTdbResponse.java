package com.jesperweijnen.trivia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenTdbResponse {
    @JsonProperty("response_code")
    private int responseCode;
    private List<OpenTdbQuestion> results;
}

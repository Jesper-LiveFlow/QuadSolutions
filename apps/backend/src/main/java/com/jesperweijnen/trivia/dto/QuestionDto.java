package com.jesperweijnen.trivia.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private final String type;
    private final String category;
    private final String question;
    private final List<String> answers;

    @JsonIgnore // Is not send to frontend/user
    private final String correctAnswer;
}

package com.jesperweijnen.trivia.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultDto {
    private final byte score;
    private final byte totalQuestions;
    private final List<String> userAnswers;
    private final List<String> correctAnswers;
}

package com.jesperweijnen.trivia.model;

import lombok.Data;

import java.util.List;

@Data
public class OpenTdbQuestion {
    private String type;
    private String difficulty;
    private String category;
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;
}

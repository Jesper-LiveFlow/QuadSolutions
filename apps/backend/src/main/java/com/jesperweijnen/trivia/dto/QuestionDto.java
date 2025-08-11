package com.jesperweijnen.trivia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private final String type;
    private final String category;
    private final String question;
    private final List<String> answers;
}

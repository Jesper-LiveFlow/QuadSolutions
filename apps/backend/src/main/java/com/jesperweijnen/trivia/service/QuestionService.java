package com.jesperweijnen.trivia.service;

import com.jesperweijnen.trivia.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> fetchQuestions(String uuid);
}

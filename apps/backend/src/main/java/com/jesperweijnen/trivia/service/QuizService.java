package com.jesperweijnen.trivia.service;

import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.ResultDto;

import java.util.List;

public interface QuizService {
    List<QuestionDto> fetchQuestions(String uuid);

    ResultDto fetchResult(String uuid, List<String> userAnswers);
}

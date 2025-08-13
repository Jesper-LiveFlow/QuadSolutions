package com.jesperweijnen.trivia.client;

import com.jesperweijnen.trivia.dto.QuestionDto;

import java.util.List;

public interface TriviaApiClient {
    List<QuestionDto> fetchQuestions();

    List<String> getCorrectAnswers(List<QuestionDto> questions);
}

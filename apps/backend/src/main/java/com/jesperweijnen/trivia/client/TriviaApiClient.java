package com.jesperweijnen.trivia.client;

import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.ResultDto;

import java.util.List;

public interface TriviaApiClient {
    List<QuestionDto> fetchQuestions(String uuid);
}

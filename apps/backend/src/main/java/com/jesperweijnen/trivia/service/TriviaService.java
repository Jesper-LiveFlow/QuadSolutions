package com.jesperweijnen.trivia.service;

import com.jesperweijnen.trivia.client.TriviaApiClient;
import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.ResultDto;
import com.jesperweijnen.trivia.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Trivia business logic.
 */
@Service
@AllArgsConstructor
public class TriviaService {
    private final TriviaApiClient triviaApiClient;
    private final AnswerRepository answerRepository;

    /**
     * Fetches trivia questions from a Trivia API Client and saves correct answers in a Answer Repository
     *
     * @param uuid unique identifier for the quiz session/player
     * @return list of question DTOs
     */
    public List<QuestionDto> getQuestions(String uuid) {
        // Get questions and correct answers
        var questions = triviaApiClient.fetchQuestions();
        var correctAnswers = triviaApiClient.getCorrectAnswers(questions);

        // Save correct answers in AnswerRepository
        answerRepository.saveAnswers(uuid, correctAnswers);

        // Return questions
        return questions;
    }

    /**
     * Calculates the trivia result for a given UUID based on the user-submitted answers.
     *
     * @param uuid unique identifier for the quiz session/player
     * @param userAnswers list of answers submitted by the user
     * @return ResultDto containing the score, total questions, user answers, and correct answers
     */
    public ResultDto getResult(String uuid, List<String> userAnswers) {
        // Get the correct answers
        var correctAnswers = answerRepository.getAnswers(uuid);

        // Get score and total number of questions
        byte score = ResultCalculatorService.calculateScore(userAnswers, correctAnswers);
        byte totalQuestions = (byte) correctAnswers.size();

        // Return result
        return new ResultDto(score, totalQuestions, userAnswers, correctAnswers);
    }
}

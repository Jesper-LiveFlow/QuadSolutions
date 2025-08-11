package com.jesperweijnen.trivia.service;

import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.ResultDto;
import com.jesperweijnen.trivia.model.OpenTdbResponse;
import com.jesperweijnen.trivia.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TriviaService {
    private final AnswerRepository answerRepository;

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

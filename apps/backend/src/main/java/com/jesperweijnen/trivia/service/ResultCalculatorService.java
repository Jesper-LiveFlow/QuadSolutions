package com.jesperweijnen.trivia.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ResultCalculatorService {
    /**
     * Calculates the score by comparing user answers with correct answers.
     *
     * @param userAnswers    list of answers provided by the user
     * @param correctAnswers list of correct answers stored in the repository
     * @return number of correct answers matched
     */
    public static byte calculateScore(List<String> userAnswers, List<String> correctAnswers) {
        // Make sure there is at least 1 user and 1 correct answer
        if (userAnswers == null || correctAnswers == null) {
            return 0;
        }

        // Count the score
        return (byte) IntStream.range(0, Math.min(userAnswers.size(), correctAnswers.size()))
                .filter(index -> userAnswers.get(index).equalsIgnoreCase(correctAnswers.get(index)))
                .count();
    }
}

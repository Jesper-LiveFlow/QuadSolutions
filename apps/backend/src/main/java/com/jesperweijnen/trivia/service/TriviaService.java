package com.jesperweijnen.trivia.service;

import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.ResultDto;
import com.jesperweijnen.trivia.model.OpenTdbQuestion;
import com.jesperweijnen.trivia.model.OpenTdbResponse;
import com.jesperweijnen.trivia.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Trivia business logic.
 */
@Service
@RequiredArgsConstructor
public class TriviaService implements QuizService {
    private final AnswerRepository answerRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    // API url for Open Trivia DB
    @Value("${third-party.opentdb.url}")
    private String opentdbUrl;
    // Number of questions to fetch from Open Trivia DB
    @Value("${third-party.opentdb.questions}")
    private byte questionTotal;

    /**
     * Fetches trivia questions from the Open Trivia Database API for a given UUID.
     *
     * @param uuid unique identifier for the quiz session/player
     * @return list of question DTOs with shuffled answers, excluding the correct answer data
     * @throws RuntimeException if the external API response is invalid or empty
     */
    @Override
    public List<QuestionDto> fetchQuestions(String uuid) {
        // Fetch response from openTdb
        var response = restTemplate.getForObject(opentdbUrl + "?amount=" + questionTotal, OpenTdbResponse.class);

        // Check if response was returned successfully and with non-null results
        if (response == null || response.getResponseCode() != 0 || response.getResults() == null) {
            throw new RuntimeException("Failed to fetch trivia questions");
        }
        // We can now assume that the questions are in the response's results
        var questions = response.getResults();

        // Save correct answers in AnswerRepository
        answerRepository.saveAnswers(uuid, getCorrectAnswers(uuid, questions));

        // Map questions to QuestionDto with shuffled answers (otherwise the correct answer will always be as last)
        return mapQuestions(questions);
    }

    /**
     * Calculates the quiz result for a given UUID based on the user-submitted answers.
     *
     * @param uuid unique identifier for the quiz session/player
     * @param userAnswers list of answers submitted by the user
     * @return ResultDto containing the score, total questions, user answers, and correct answers
     */
    @Override
    public ResultDto fetchResult(String uuid, List<String> userAnswers) {
        // Get the correct answers
        var correctAnswers = answerRepository.getAnswers(uuid);

        // Get score and total number of questions
        byte score = calculateScore(userAnswers, correctAnswers);
        byte totalQuestions = (byte) correctAnswers.size();

        // Return result
        return new ResultDto(score, totalQuestions, userAnswers, correctAnswers);
    }

    /**
     * Extracts the list of correct answers from the given list of trivia questions.
     *
     * @param uuid      the unique identifier for the current game session (not used in this method but can be used for logging or future enhancements)
     * @param questions the list of questions received from the Open Trivia Database API
     * @return a list of correct answers corresponding to each question, preserving order
     */
    private List<String> getCorrectAnswers(String uuid, List<OpenTdbQuestion> questions) {
        // Extract correct answers using streams: OpenTdbResponse -> List of OpenTdbQuestion -> Map to list of
        // (String) correctAnswers -> Convert to list
        return questions
                .stream()
                .map(OpenTdbQuestion::getCorrectAnswer)
                .toList();
    }

    /**
     * Maps a list of OpenTdbQuestion objects to a list of QuestionDto objects.
     *
     * @param questions the raw list of questions from the external API
     * @return a list of QuestionDto objects with answers shuffled and ready for presentation
     */
    private List<QuestionDto> mapQuestions(List<OpenTdbQuestion> questions) {
        return questions.stream()
                .map(this::mapToQuestionDto)
                .toList();
    }

    /**
     * Converts a single OpenTdbQuestion into a QuestionDto.
     * Also combines correct and incorrect answers into one shuffled list.
     *
     * @param question the raw question object from the external API
     * @return a QuestionDto containing question details and shuffled answer choices
     */
    private QuestionDto mapToQuestionDto(OpenTdbQuestion question) {
        List<String> allAnswers = new ArrayList<>(question.getIncorrectAnswers());
        allAnswers.add(question.getCorrectAnswer());
        Collections.shuffle(allAnswers);

        return new QuestionDto(
                question.getType(),
                question.getCategory(),
                question.getQuestion(),
                allAnswers
        );
    }

    /**
     * Calculates the score by comparing user answers with correct answers.
     *
     * @param userAnswers    list of answers provided by the user
     * @param correctAnswers list of correct answers stored in the repository
     * @return number of correct answers matched
     */
    private byte calculateScore(List<String> userAnswers, List<String> correctAnswers) {
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

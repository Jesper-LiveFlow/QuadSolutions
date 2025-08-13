package com.jesperweijnen.trivia.client;

import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.external.OpenTdbQuestionDto;
import com.jesperweijnen.trivia.dto.external.OpenTdbResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenTdbClient implements TriviaApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final int OPENTDB_SUCCESS_CODE = 0;

    // API url for Open Trivia DB
    @Value("${third-party.opentdb.url}")
    private String opentdbUrl;
    // Number of questions to fetch from Open Trivia DB
    @Value("${third-party.opentdb.questions}")
    private byte questionTotal;

    /**
     * Fetches trivia questions from the Open Trivia Database API for a given UUID.
     *
     * @return list of question DTOs with shuffled answers, excluding the correct answer data
     * @throws RuntimeException if the external API response is invalid or empty
     */
    @Override
    public List<QuestionDto> fetchQuestions() {
        // Fetch response from openTdb
        var response = restTemplate.getForObject(opentdbUrl + "?amount=" + questionTotal, OpenTdbResponseDto.class);

        // Check if response was returned successfully and with non-null results
        if (response == null || response.getResponseCode() != OPENTDB_SUCCESS_CODE || response.getResults() == null) {
            throw new RuntimeException("Failed to fetch trivia questions");
        }
        // We can now assume that the questions are in the response's results
        var questions = response.getResults();

        // Map questions to QuestionDto with shuffled answers (otherwise the correct answer will always be as last)
        return mapQuestions(questions);
    }

    /**
     * Extracts the list of correct answers from the given list of trivia questions.
     *
     * @param questions the list of questions received from the Open Trivia Database API
     * @return a list of correct answers corresponding to each question, preserving order
     */
    @Override
    public List<String> getCorrectAnswers(List<QuestionDto> questions) {
        // Extract correct answers using streams: List of QuestionDto -> Map to list of
        // (String) correctAnswers -> Convert to list
        return questions
                .stream()
                .map(QuestionDto::getCorrectAnswer)
                .toList();
    }

    /**
     * Maps a list of OpenTdbQuestionDto objects to a list of QuestionDto objects.
     *
     * @param questions the raw list of questions from the external API
     * @return a list of QuestionDto objects with answers shuffled and ready for presentation
     */
    private List<QuestionDto> mapQuestions(List<OpenTdbQuestionDto> questions) {
        return questions
                .stream()
                .map(this::mapToQuestionDto)
                .toList();
    }

    /**
     * Converts a single OpenTdbQuestionDto into a QuestionDto.
     * Also combines correct and incorrect answers into one shuffled list.
     *
     * @param question the raw question object from the external API
     * @return a QuestionDto containing question details and shuffled answer choices
     */
    private QuestionDto mapToQuestionDto(OpenTdbQuestionDto question) {
        List<String> allAnswers = new ArrayList<>(question.getIncorrectAnswers());
        allAnswers.add(question.getCorrectAnswer());
        Collections.shuffle(allAnswers);

        return new QuestionDto(
                question.getType(),
                question.getCategory(),
                question.getQuestion(),
                allAnswers,
                question.getCorrectAnswer()
        );
    }
}

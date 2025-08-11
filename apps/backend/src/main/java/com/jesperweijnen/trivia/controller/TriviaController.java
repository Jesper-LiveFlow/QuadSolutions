package com.jesperweijnen.trivia.controller;

import com.jesperweijnen.trivia.dto.QuestionDto;
import com.jesperweijnen.trivia.dto.ResultDto;
import com.jesperweijnen.trivia.service.TriviaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TriviaController {

    private final TriviaService triviaService;

    /**
     * GET /api/questions?uuid=<uuid>
     * Returns 10 questions for given uuid.
     * Questions are saved in memory under uuid.
     */
    @GetMapping("questions")
    public List<QuestionDto> getQuestions(@RequestParam(required = true, name = "uuid") String uuid) {
        return triviaService.getQuestions(uuid);
    }

    /**
     * POST /api/checkanswers?=uuid=<uuid>
     * Returns result of quiz.
     */
    @PostMapping("checkanswers")
    public ResultDto checkAnswers(
            @RequestParam(required = true, name = "uuid") String uuid,
            @RequestBody List<String> userAnswers
    ) {
        return triviaService.getResult(uuid, userAnswers);
    }
}

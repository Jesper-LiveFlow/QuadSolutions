package com.jesperweijnen.trivia.repository;

import java.util.List;

public interface AnswerRepository {
    void saveAnswers(String uuid, List<String> answers);

    List<String> getAnswers(String uuid);
}

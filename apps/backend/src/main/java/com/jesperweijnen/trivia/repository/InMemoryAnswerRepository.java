package com.jesperweijnen.trivia.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAnswerRepository implements AnswerRepository {
    private final Map<String, List<String>> answersStore = new ConcurrentHashMap<>();

    /**
     * Save (or overwrite) correct answers for a given uuid.
     */
    @Override
    public void saveAnswers(String uuid, List<String> answers) {
        answersStore.put(uuid, answers);
    }

    /**
     * Retrieve correct answers for a given uuid.
     * @return list of correct answers or null if not found
     */
    @Override
    public List<String> getAnswers(String uuid) {
        return answersStore.get(uuid);
    }
}

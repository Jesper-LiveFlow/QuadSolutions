package com.jesperweijnen.trivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriviaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TriviaApplication.class, args);

        System.out.println("TriviaApplication started.");
    }
}

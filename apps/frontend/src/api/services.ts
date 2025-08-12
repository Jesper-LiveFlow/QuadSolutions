/**
 * services.ts
 * ---------------------------------------
 * Contains all API requests
 *
 */

// IMPORTS ------------------------------------------------
import baseApi from "@/api/baseApi";
import { QUESTIONS, CHECK_ANSWERS } from "@/api/apiEndpoints";
import type { Question } from "@/types/Question";
import type { Result } from "@/types/Result";
// --------------------------------------------------------

// GET REQUESTS -------------------------------------------
// Mock questions for demo
const questions: Question[] = [
  {
    type: "multiple",
    category: "General Knowledge",
    question: "What is the capital of France?",
    answers: ["Paris", "Berlin", "Madrid", "London"],
  },
  {
    type: "boolean",
    category: "Science & Nature",
    question: "The chemical symbol for gold is Au.",
    answers: ["True", "False"],
  },
  {
    type: "multiple",
    category: "Geography",
    question: "Which country has the largest population in the world?",
    answers: ["China", "India", "United States", "Indonesia"],
  },
  {
    type: "multiple",
    category: "History",
    question: "Who was the first President of the United States?",
    answers: ["George Washington", "Thomas Jefferson", "Abraham Lincoln", "John Adams"],
  },
  {
    type: "boolean",
    category: "Mathematics",
    question: "Pi is approximately equal to 3.1416.",
    answers: ["True", "False"],
  },
  {
    type: "multiple",
    category: "Science & Nature",
    question: "What gas do plants primarily absorb from the atmosphere?",
    answers: ["Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"],
  },
  {
    type: "boolean",
    category: "Music",
    question: "The Beatles were a band from the United States.",
    answers: ["True", "False"],
  },
  {
    type: "multiple",
    category: "Sports",
    question: "In tennis, what is the term for a score of zero?",
    answers: ["Love", "Nil", "Blank", "Zero"],
  },
  {
    type: "multiple",
    category: "Computers",
    question: "Which company developed the Windows operating system?",
    answers: ["Microsoft", "Apple", "Google", "IBM"],
  },
  {
    type: "boolean",
    category: "Animals",
    question: "Sharks are mammals.",
    answers: ["True", "False"],
  },
];

/**
 * Gets a new set of questions
 * @param {string} [uuid] - Universally unique identifier for identifying session/user
 * @returns {Promise<Question[]>} List of trivia questions
 */
export const getQuestions = async (uuid: string): Promise<Question[]> => {
  try {
    const { data } = await baseApi.get<Question[]>(`/${QUESTIONS}?uuid=${uuid}`);

    return data;
  } catch (error) {
    throw error;
  }
};
// --------------------------------------------------------

// POST REQUESTS ------------------------------------------
/**
 * Check user answers
 * @param {string} [uuid] - Universally unique identifier for identifying session/user
 * @returns {Result} Result of trivia quiz
 */
export const checkAnswers = async (uuid: string, userAnswers: unknown): Promise<Result> => {
  try {
    const { data } = await baseApi.post<Result>(`/${CHECK_ANSWERS}?uuid=${uuid}`, userAnswers);

    return data;
  } catch (error) {
    throw error;
  }
};
// --------------------------------------------------------

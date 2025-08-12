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
/**
 * Gets a new set of questions
 * @param {string} [uuid] - Universally unique identifier for identifying session/user
 * @returns {Promise<Question[]>} List of trivia questions
 */
export const getQuestions = async (uuid: string): Promise<Question[]> => {
  try {
    const response = await baseApi.get<Question[]>(`/${QUESTIONS}?uuid=${uuid}`);

    return response.data;
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
    const response = await baseApi.post<Result>(`/${CHECK_ANSWERS}?uuid=${uuid}`, userAnswers);

    return response.data;
  } catch (error) {
    throw error;
  }
};
// --------------------------------------------------------

/**
 * services.ts
 * ---------------------------------------
 * Contains all API requests
 *
 */

// IMPORTS ------------------------------------------------
import baseApi from "@/api/baseApi";
import { QUESTIONS, CHECK_ANSWERS } from "@/api/apiEndpoints";
// --------------------------------------------------------

// GET REQUESTS -------------------------------------------
/**
 * Gets a new set of questions
 * @param {string} [uuid] - Universally unique identifier for identifying session/user
 * @returns {unknown} List of trivia questions
 */
export const getQuestions = async (uuid: string): Promise<unknown> => {
  try {
    const response = await baseApi.get<unknown>(`/${QUESTIONS}?uuid=${uuid}`);

    return response;
  } catch (error) {
    throw error;
  }
};
// --------------------------------------------------------

// POST REQUESTS ------------------------------------------
/**
 * Check user answers
 * @param {string} [uuid] - Universally unique identifier for identifying session/user
 * @returns {unknown} Result of trivia quiz
 */
export const checkAnswers = async (uuid: string, userAnswers: unknown): Promise<unknown> => {
  try {
    const response = await baseApi.post<unknown>(`/${CHECK_ANSWERS}?uuid=${uuid}`, userAnswers);

    return response;
  } catch (error) {
    throw error;
  }
};
// --------------------------------------------------------

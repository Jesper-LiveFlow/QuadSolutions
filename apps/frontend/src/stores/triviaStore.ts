/** VUE IMIPORTS */
import { ref } from "vue";
/** PINIA IMPORTS */
import { defineStore } from "pinia";
/** TYPE IMPORTS */
import type { Question } from "@/types/Question";
import type { Result } from "@/types/Result";

// Define Pinia Store
export const useTriviaStore = defineStore("trivia", () => {
  /** STATES */
  const questions = ref<Question[]>([]);
  const currentQuestion = ref<Question>(null);
  const currentQuestionNum = ref(0);
  const totalQuestions = ref(0);
  const userAnswers = ref<string[]>([]);
  const result = ref<Result>(null);
  /** ACTIONS */
  const setQuestions = (newQuestions: Question[]) => {
    // Set new questions and init states
    questions.value = newQuestions;
    currentQuestionNum.value = 0;
    currentQuestion.value = questions.value[currentQuestionNum.value];
    totalQuestions.value = newQuestions.length;
  };

  const saveSelectedAnswer = (selectedAnswer: string) => {
    userAnswers.value.push(selectedAnswer);
  };

  const nextQuestion = (selectedAnswer: string) => {
    // Save selected answer
    saveSelectedAnswer(selectedAnswer);
    // Go to next question
    currentQuestionNum.value++;
    currentQuestion.value = questions.value[currentQuestionNum.value];
  };

  const reset = () => {
    questions.value = [];
    currentQuestion.value = null;
    currentQuestionNum.value = 0;
    totalQuestions.value = 0;
    userAnswers.value = [];
    result.value = null;
  };

  return {
    // States
    questions,
    currentQuestion,
    currentQuestionNum,
    totalQuestions,
    userAnswers,
    result,
    // Actions
    setQuestions,
    saveSelectedAnswer,
    nextQuestion,
    reset,
  };
});

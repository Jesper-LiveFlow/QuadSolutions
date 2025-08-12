/** VUE IMIPORTS */
import { ref } from "vue";
/** PINIA IMPORTS */
import { defineStore } from "pinia";
/** TYPE IMPORTS */
import type { Question } from "@/types/Question";

// Define Pinia Store
export const useTriviaStore = defineStore("trivia", () => {
  /** STATES */
  const questions = ref<Question[]>([]);
  const currentQuestion = ref<Question>(null);
  const currentQuestionNum = ref(0);
  const totalQuestions = ref(0);
  const userAnswers = ref<string[]>([]);
  /** ACTIONS */
  const setQuestions = (newQuestions: Question[]) => {
    // Set new questions and init states
    questions.value = newQuestions;
    currentQuestionNum.value = 0;
    currentQuestion.value = questions.value[currentQuestionNum.value];
    totalQuestions.value = newQuestions.length;
  };

  const nextQuestion = (selectedAnswer: string) => {
    // Save selected answer
    userAnswers.value.push(selectedAnswer);
    // Go to next question
    currentQuestionNum.value++;
    currentQuestion.value = questions.value[currentQuestionNum.value];
  };

  return {
    // States
    questions,
    currentQuestion,
    currentQuestionNum,
    totalQuestions,
    userAnswers,
    // Actions
    setQuestions,
    nextQuestion,
  };
});

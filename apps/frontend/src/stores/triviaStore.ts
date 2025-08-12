/** VUE IMIPORTS */
import { ref, computed } from "vue";
/** PINIA IMPORTS */
import { defineStore } from "pinia";
/** TYPE IMPORTS */
import type { Question } from "@/types/Question";

// Define Pinia Store
export const useTriviaStore = defineStore("trivia", () => {
  /** STATES */
  const questions = ref<Question[]>([]);
  const currentQuestion = ref(0);
  const totalQuestions = ref(0);
  const userAnswers = ref<string[]>([]);
  /** COMPUTED STATES */
  // ...
  /** ACTIONS */
  // ...

  return {
    // States
    questions,
    currentQuestion,
    totalQuestions,
    userAnswers,
    // Computed States
    // ...
    // Actions
    // ...
  };
});

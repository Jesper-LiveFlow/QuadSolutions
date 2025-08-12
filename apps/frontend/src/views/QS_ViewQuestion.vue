<template>
  <main
    class="min-h-screen bg-gradient-to-tr from-indigo-400 via-pink-400 to-yellow-300 flex flex-col animate-slow-bg-move p-6"
    v-if="triviaStore.totalQuestions > 0"
  >
    <!-- Progress Bar -->
    <QS_ProgressBar :current="triviaStore.currentQuestionNum" :total="triviaStore.totalQuestions" />

    <!-- Question + Answers -->
    <div class="flex-grow flex flex-col justify-center items-center text-center space-y-8">
      <!-- Timer -->
      <QS_Timer :time-left="timeLeft" />

      <!-- Question -->
      <h2
        class="text-3xl md:text-4xl font-bold text-white drop-shadow-lg bg-black/20 px-6 py-4 rounded-2xl"
        v-html="triviaStore.currentQuestion.question"
      ></h2>

      <!-- Answers -->
      <QS_AnswerList
        :answers="triviaStore.currentQuestion.answers"
        v-model:selected="selectedAnswer"
      />

      <!-- Confirm Button -->
      <QS_ConfirmButton :disabled="!selectedAnswer" @confirm="confirmAnswer" />
    </div>
  </main>
</template>

<script setup lang="ts">
/** VUE IMPORTS */
import { ref, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
/** COMPONENT IMPORTS */
import QS_ProgressBar from "@/components/question/QS_ProgressBar.vue";
import QS_Timer from "@/components/question/QS_Timer.vue";
import QS_AnswerList from "@/components/question/QS_AnswerList.vue";
import QS_ConfirmButton from "@/components/question/QS_ConfirmButton.vue";
/** COMPOSABLE IMPORTS */
import { useUuid } from "@/composables/useUserId";
const { userUuid } = useUuid();
/** SERVICE IMPORTS */
import { checkAnswers } from "@/api/services";
/** STORE IMPORTS */
import { useTriviaStore } from "@/stores/triviaStore";
const triviaStore = useTriviaStore();

// Define data
const selectedAnswer = ref<string>("");
const timeLeft = ref(15);
let timer: ReturnType<typeof setInterval> | null = null;

// Define methods
const startTimer = () => {
  timeLeft.value = 15;
  timer = setInterval(() => {
    if (timeLeft.value > 0) {
      timeLeft.value--;
    } else {
      confirmAnswer();
    }
  }, 1000);
};

const stopTimer = () => {
  if (timer) clearInterval(timer);
};

const confirmAnswer = async () => {
  // Stop timer for current question
  stopTimer();

  // Check if all questions have been answered
  if (triviaStore.currentQuestionNum < triviaStore.totalQuestions - 1) {
    // Save answer and go to next question
    triviaStore.nextQuestion(selectedAnswer.value);

    // Restart timer
    startTimer();
  } else {
    try {
      // Save last answer
      triviaStore.saveSelectedAnswer(selectedAnswer.value);

      // Check answers
      const result = await checkAnswers(userUuid.value, triviaStore.userAnswers);

      // Check if there was a valid response
      if (result == null || !result) {
        throw new Error("No result found");
      }

      // Set result in store
      triviaStore.result = result;

      // Go to result view
      router.push({ name: "result" });
    } catch {
      alert("There was an error trying to check your answers.");

      // Reset trivia store
      triviaStore.reset();

      // Go to home view
      router.push({ name: "home" });
    }
  }

  // Clear selected answer
  selectedAnswer.value = "";
};

// Define lifecycle hooks
onMounted(() => {
  // Check if there are any questions saved in memory
  if (triviaStore.questions.length == 0) {
    // Send user back to home page
    router.push({ name: "home" });
  }

  // Start 15 seconds countdown
  startTimer();
});
</script>

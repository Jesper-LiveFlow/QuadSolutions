<template>
  <main
    class="min-h-screen bg-gradient-to-tr from-indigo-400 via-pink-400 to-yellow-300 flex flex-col animate-slow-bg-move p-6"
    v-if="triviaStore.result"
  >
    <!-- Header -->
    <div class="text-center mb-8">
      <h1 class="text-4xl md:text-5xl font-extrabold text-white drop-shadow-lg">
        Quiz Complete! 🎉
      </h1>
    </div>

    <!-- Results Content -->
    <div
      class="flex-grow flex flex-col items-center justify-center space-y-8 max-w-4xl mx-auto w-full"
    >
      <!-- Score Card -->
      <QS_ScoreCard
        :score="triviaStore.result.score"
        :total-questions="triviaStore.result.totalQuestions"
      />

      <!-- Answer Summary -->
      <QS_AnswerSummary
        :user-answers="triviaStore.result.userAnswers"
        :correct-answers="triviaStore.result.correctAnswers"
      />

      <!-- Action Buttons -->
      <QS_ResultActions @go-home="goHome" />
    </div>
  </main>

  <!-- Loading/Error State -->
  <main
    v-else
    class="min-h-screen bg-gradient-to-tr from-indigo-400 via-pink-400 to-yellow-300 flex flex-col animate-slow-bg-move p-6"
  >
    <div class="flex-grow flex items-center justify-center">
      <div class="bg-white/20 backdrop-blur-sm rounded-2xl p-8 shadow-xl text-center">
        <h2 class="text-3xl font-bold text-white mb-4 drop-shadow-lg">No Results Found</h2>
        <p class="text-white/90 mb-6">It seems like you haven't completed a quiz yet.</p>
        <button
          class="cursor-pointer bg-gradient-to-r from-yellow-400 via-pink-500 to-purple-600 hover:from-purple-600 hover:to-pink-500 text-white font-bold text-xl px-8 py-4 rounded-full shadow-lg transform transition-transform active:scale-95 focus:outline-none focus:ring-4 focus:ring-pink-300"
          @click="goHome"
        >
          🏠 Go Home
        </button>
      </div>
    </div>
  </main>
</template>

<script setup>
/** VUE IMPORTS */
import { onMounted } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
/** COMPONENT IMPORTS */
import QS_ScoreCard from "@/components/result/QS_ScoreCard.vue";
import QS_AnswerSummary from "@/components/result/QS_AnswerSummary.vue";
import QS_ResultActions from "@/components/result/QS_ResultActions.vue";
/** STORE IMPORTS */
import { useTriviaStore } from "@/stores/triviaStore";
const triviaStore = useTriviaStore();

const goHome = () => {
  // Reset the store state
  triviaStore.reset();

  // Navigate to home
  router.push({ name: "home" });
};

// Define lifecycle hooks
onMounted(() => {
  // Check if there are results to display
  if (!triviaStore.result) {
    console.warn("No result data found, redirecting to home");
    router.push({ name: "home" });
  }
});
</script>

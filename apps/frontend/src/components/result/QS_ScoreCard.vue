<template>
  <div class="bg-white/20 backdrop-blur-sm rounded-2xl p-8 shadow-xl text-center">
    <h2 class="text-4xl md:text-5xl font-bold text-white mb-4 drop-shadow-lg">Your Score</h2>

    <!-- Score Circle -->
    <div class="relative w-32 h-32 mx-auto mb-6">
      <!-- Background circle -->
      <div
        class="absolute inset-0 w-full h-full rounded-full bg-white/20 border-4 border-white/30"
      ></div>

      <!-- Progress ring -->
      <svg class="absolute inset-0 w-full h-full transform -rotate-90" viewBox="0 0 120 120">
        <!-- Background circle -->
        <circle
          cx="60"
          cy="60"
          r="54"
          fill="none"
          stroke="rgba(255,255,255,0.2)"
          stroke-width="4"
        />
        <!-- Progress circle -->
        <circle
          cx="60"
          cy="60"
          r="54"
          fill="none"
          stroke="#10b981"
          stroke-width="4"
          stroke-linecap="round"
          :stroke-dasharray="`${
            progressCircumference * (score / totalQuestions)
          } ${progressCircumference}`"
          class="transition-all duration-1000 ease-out"
        />
      </svg>

      <!-- Score text -->
      <div class="absolute inset-0 flex items-center justify-center">
        <div class="text-3xl font-extrabold text-white drop-shadow-lg">
          {{ score }}/{{ totalQuestions }}
        </div>
      </div>
    </div>

    <!-- Percentage -->
    <div class="text-2xl font-bold text-white mb-4 drop-shadow-lg">{{ percentage }}%</div>

    <!-- Performance Message -->
    <div class="text-lg text-white/90 font-medium drop-shadow">
      {{ performanceMessage }}
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";

const props = defineProps({
  score: {
    type: Number,
    required: true,
  },
  totalQuestions: {
    type: Number,
    required: true,
  },
});

const percentage = computed(() => Math.round((props.score / props.totalQuestions) * 100));

const progressCircumference = computed(() => 2 * Math.PI * 54); // 54 is the radius of the circle

const performanceMessage = computed(() => {
  const perc = percentage.value;
  if (perc === 100) return "Perfect! Outstanding work! 🎉";
  if (perc >= 90) return "Excellent! You're a trivia master! 🌟";
  if (perc >= 80) return "Great job! Well done! 👏";
  if (perc >= 70) return "Good work! Keep it up! 👍";
  if (perc >= 60) return "Not bad! Room for improvement! 💪";
  return "Better luck next time! Practice makes perfect! 📚";
});
</script>

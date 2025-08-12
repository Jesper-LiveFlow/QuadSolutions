<template>
  <div class="bg-white/20 backdrop-blur-sm rounded-2xl p-6 shadow-xl">
    <h3 class="text-2xl font-bold text-white mb-6 text-center drop-shadow-lg">Answer Summary</h3>

    <div class="space-y-4 max-h-96 overflow-y-auto">
      <div
        v-for="(userAnswer, index) in userAnswers"
        :key="index"
        class="bg-white/10 rounded-xl p-4 border-l-4"
        :class="[
          userAnswer === correctAnswers[index]
            ? 'border-green-400 bg-green-500/10'
            : 'border-red-400 bg-red-500/10',
        ]"
      >
        <div class="flex items-start justify-between mb-2">
          <span class="text-sm font-semibold text-white/80"> Question {{ index + 1 }} </span>
          <div class="flex items-center">
            <span
              class="text-lg font-bold"
              :class="[userAnswer === correctAnswers[index] ? 'text-green-300' : 'text-red-300']"
            >
              {{ userAnswer === correctAnswers[index] ? "✓" : "✗" }}
            </span>
          </div>
        </div>

        <div class="space-y-2">
          <div class="text-white/90">
            <span class="font-medium">Your answer:</span>
            <span
              class="ml-2 px-2 py-1 rounded text-sm"
              :class="[
                userAnswer === correctAnswers[index]
                  ? 'bg-green-500/30 text-green-200'
                  : 'bg-red-500/30 text-red-200',
              ]"
            >
              {{ userAnswer || "No answer" }}
            </span>
          </div>

          <div v-if="userAnswer !== correctAnswers[index]" class="text-white/90">
            <span class="font-medium">Correct answer:</span>
            <span class="ml-2 px-2 py-1 rounded text-sm bg-green-500/30 text-green-200">
              {{ correctAnswers[index] }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  userAnswers: {
    type: Array,
    required: true,
  },
  correctAnswers: {
    type: Array,
    required: true,
  },
});
</script>

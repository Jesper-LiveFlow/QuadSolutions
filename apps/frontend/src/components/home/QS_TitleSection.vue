<template>
  <section class="text-center px-6">
    <h1
      class="text-4xl md:text-6xl sm:text font-extrabold text-transparent bg-clip-text bg-gradient-to-r text-yellow-400 leading-tight whitespace-pre-line select-none drop-shadow-lg"
    >
      Quad Solutions
      <br />
      Trivia App
    </h1>

    <button
      class="cursor-pointer mt-6 md:mt-12 bg-gradient-to-r from-yellow-400 via-pink-500 to-purple-600 hover:from-purple-600 hover:to-pink-500 text-white font-bold text-xl px-14 py-4 rounded-full shadow-lg transform transition-transform active:scale-95 focus:outline-none focus:ring-4 focus:ring-pink-300"
      aria-label="Start Trivia"
      @click="emit('start-trivia')"
    >
      Start Trivia
    </button>
  </section>
</template>

<script setup lang="ts">
const emit = defineEmits<{
  (e: "start-trivia"): void;
}>();
</script>

<template>
  <div class="grid gap-4 w-full max-w-lg">
    <button
      v-for="answer in answers"
      :key="answer"
      class="cursor-pointer bg-white/70 hover:bg-white text-gray-800 font-semibold py-3 px-6 rounded-lg shadow-lg transform transition-transform active:scale-95"
      :class="{ 'ring-4 ring-yellow-400 scale-105': selected === answer }"
      @click="$emit('update:selected', answer)"
    >
      <span v-html="answer"></span>
    </button>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  answers: readonly string[];
  selected?: string;
}>();
</script>

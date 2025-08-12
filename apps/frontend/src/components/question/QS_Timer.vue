<template>
  <div class="flex items-center space-x-3">
    <div
      class="w-12 h-12 rounded-full bg-white/30 flex items-center justify-center text-xl font-bold text-white drop-shadow-lg animate-pulse"
    >
      {{ timeLeft }}
    </div>
    <span class="text-white font-semibold">seconds left</span>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  timeLeft: number;
}>();
</script>

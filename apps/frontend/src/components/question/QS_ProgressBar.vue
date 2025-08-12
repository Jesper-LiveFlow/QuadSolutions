<template>
  <div class="w-full bg-white/30 rounded-full overflow-hidden h-6 shadow-lg">
    <div
      class="bg-gradient-to-r from-green-400 via-yellow-400 to-pink-500 h-6 transition-all duration-500 ease-out"
      :style="{ width: `${(current / total) * 100}%` }"
    ></div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  current: number;
  total: number;
}>();
</script>

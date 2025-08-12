<template>
  <button
    class="lg:w-1/3 cursor-pointer mt-6 bg-gradient-to-r from-yellow-400 via-pink-500 to-purple-600 text-white font-bold text-xl px-14 py-4 rounded-full shadow-lg transform transition-transform active:scale-95 focus:outline-none focus:ring-4 focus:ring-pink-300 disabled:opacity-50 disabled:cursor-not-allowed"
    :disabled="disabled"
    @click="$emit('confirm')"
  >
    Confirm Answer
  </button>
</template>

<script setup lang="ts">
defineProps<{
  disabled?: boolean;
}>();
</script>

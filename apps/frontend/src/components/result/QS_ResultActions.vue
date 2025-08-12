<template>
  <div class="flex flex-col sm:flex-row gap-4 justify-center items-center">
    <button
      class="w-full sm:w-auto cursor-pointer bg-gradient-to-r from-blue-500 via-teal-500 to-green-500 hover:from-green-500 hover:to-blue-500 text-white font-bold text-xl px-8 py-4 rounded-full shadow-lg transform transition-transform active:scale-95 focus:outline-none focus:ring-4 focus:ring-blue-300"
      @click="$emit('go-home')"
    >
      🏠 Home
    </button>
  </div>
</template>

<script setup>
defineEmits(["go-home"]);
</script>

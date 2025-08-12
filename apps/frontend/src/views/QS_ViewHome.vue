<template>
  <div>View Home</div>
</template>

<script setup>
/** VUE IMPORTS */
import { onMounted } from "vue";
/** COMPOSABLE IMPORTS */
import { useUuid } from "@/composables/useUserId";
const { userUuid } = useUuid();
/** SERVICE IMPORTS */
import { getQuestions } from "@/api/services";

onMounted(async () => {
  // const questions = await getQuestions();
  // console.log(questions);

  console.log("You're UUID: " + userUuid.value);
});
</script>

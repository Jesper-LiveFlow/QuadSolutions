<template>
  <div class="flex-grow flex items-center justify-center">
    <TitleSection @start-trivia="startTrivia" />
  </div>

  <CreditFooter />
</template>

<script setup>
/** VUE IMPORTS */
import { onMounted } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
/** COMPONENT IMPORTS */
import TitleSection from "@/components/home/QS_TitleSection.vue";
import CreditFooter from "@/components/home/QS_CreditFooter.vue";
/** COMPOSABLE IMPORTS */
import { useUuid } from "@/composables/useUserId";
const { userUuid } = useUuid();
/** SERVICE IMPORTS */
import { getQuestions } from "@/api/services";
/** STORE IMPORTS */
import { useTriviaStore } from "@/stores/triviaStore";

// Define Methods
const startTrivia = () => {
  router.push({ name: "question" });
};

// Define lifecycle hooks
onMounted(async () => {
  // const questions = await getQuestions();
  // console.log(questions);
  // console.log("You're UUID: " + userUuid.value);
});
</script>

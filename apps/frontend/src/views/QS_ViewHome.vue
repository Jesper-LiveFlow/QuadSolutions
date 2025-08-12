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
const startTrivia = async () => {
  // Instantiate trivia store
  const triviaStore = useTriviaStore();

  try {
    // Fetch questions from backend
    const questions = await getQuestions(userUuid.value);

    // Check if there was a valid response
    if (questions == null || !questions || questions.length === 0) {
      throw new Error("No questions found");
    }

    // Set questions in store
    triviaStore.setQuestions(questions);

    // Push user to question view
    router.push({ name: "question" });
  } catch {
    alert("There was an error trying to load new questions.");
  }
};

// Define lifecycle hooks
onMounted(async () => {
  // const questions = await getQuestions();
  // console.log(questions);
  // console.log("You're UUID: " + userUuid.value);
});
</script>

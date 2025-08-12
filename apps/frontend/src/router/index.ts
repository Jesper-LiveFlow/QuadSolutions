// IMPORTS ------------------------------------------------
/** ROUTER IMPORTS */
import { createRouter, createWebHistory } from "vue-router";

/** TYPE IMPORTS */
import type { RouteRecordRaw } from "vue-router";

/** VIEW IMPORTS */
import QS_ViewHome from "@/views/QS_ViewHome.vue";
import QS_ViewQuestion from "@/views/QS_ViewQuestion.vue";
import QS_ViewResult from "@/views/QS_ViewResult.vue";
// --------------------------------------------------------

// Routes array
const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "home",
    component: QS_ViewHome,
  },
  {
    path: "/question",
    name: "question",
    component: QS_ViewQuestion,
  },
  {
    path: "/result",
    name: "result",
    component: QS_ViewResult,
  },
];

// Create router object
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

// Export router
export default router;

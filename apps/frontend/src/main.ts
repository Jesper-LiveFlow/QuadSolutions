// IMPORTS ------------------------------------------------
import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
// --------------------------------------------------------

// Create Vue App
const app = createApp(App);

// Plugin uses
app.use(createPinia());
app.use(router);

// Mount Vue App
app.mount("#app");

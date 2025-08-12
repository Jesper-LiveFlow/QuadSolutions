import { ref } from "vue";
import { v4 as uuidv4 } from "uuid";

export const useUuid = () => {
  // Create empty user uuid
  let userUuid = ref<string>("");

  // Check if window exists (otherwise localstorage is not accessable)
  if (typeof window != "undefined") {
    // Get uuid from local storage
    let uuid = localStorage.getItem("qs_trivia_uuid");
    // Check if there is a uuid in local storage
    if (!uuid) {
      // If not, create new one and save in local storage
      uuid = uuidv4();
      localStorage.setItem("qs_trivia_uuid", uuid);
    }
    // Set user uuid to retrieved/generated uuid
    userUuid.value = uuid;
  }

  // Return user uuid
  return { userUuid };
};

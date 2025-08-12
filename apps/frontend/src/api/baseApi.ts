/**
 * baseApi.ts
 * --------------------------------
 * Base API instance
 *
 */

// IMPORTS ------------------------------------------------
import axios from "axios";
import { API_CONFIG } from "@/api/apiConfig";
import { setupInterceptors } from "@/api/apiInterceptors";
// --------------------------------------------------------

// Create base API object
const baseApi = axios.create({
  baseURL: API_CONFIG.API_URL,
  timeout: API_CONFIG.API_TIMEOUT,
});

// Setup the interceptors for the API, which handles general API errors
setupInterceptors(baseApi);

// Export base API
export default baseApi;

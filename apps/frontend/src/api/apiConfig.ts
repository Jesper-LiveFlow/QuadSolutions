/**
 * apiConfig.ts
 * --------------------------------
 * Central API configuration file
 *
 */

// BASE_URL: API url of backend
const BASE_URL: string = "http://localhost:8080/api";
// API_TIMEOUT: API timeout in seconds
const API_TIMEOUT: number = 10000;
// DEFAULT_HEADERS: Default request headers to tell server what kind of data is sent
const DEFAULT_HEADERS: string = "application/json";

// Export API_CONFIG object
export const API_CONFIG = {
  API_URL: BASE_URL,
  API_TIMEOUT,
  DEFAULT_HEADERS,
};

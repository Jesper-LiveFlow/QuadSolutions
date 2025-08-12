/**
 * apiInterceptors.ts
 * ---------------------------------------
 * Interceptors for API requests
 * -
 */

// IMPORTS ------------------------------------------------
import type { AxiosInstance, AxiosResponse, AxiosError } from "axios";
// --------------------------------------------------------

const responseInterceptor = (response: AxiosResponse) => {
  return response;
};

const responseErrorInterceptor = (error: AxiosError) => {
  return error;
};

export const setupInterceptors = (baseApi: AxiosInstance) => {
  // Use response interceptors that are configured in this file
  baseApi.interceptors.response.use(responseInterceptor, responseErrorInterceptor);
};

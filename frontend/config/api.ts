/* eslint-disable no-unused-vars */
import { create } from 'apisauce';
import ApiUrlConfig from './envConfig';

let token = 1;

enum StatusCode {
  Unauthorized = 401,
  InternalServerError = 500,
}

export const API = create({
  baseURL: ApiUrlConfig.API_URL,
  headers: { Authentication: `Bearer ${token}` },
});

export const ROUTES = {
  LOGIN: `/api/login/authenticate/`,
  GET_ITEM_LIST: `/api/public/item/list`,
  GET_USER: `api/private/user/`,
  GET_OTHER_USERS: `/api/public/user/`,
};

API.axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    const statusCode: number = error!.response!.status;
    switch (statusCode) {
      case StatusCode.Unauthorized: {
        // Toast to tell user login session has expired
        break;
      }
      case StatusCode.InternalServerError: {
        // Toast to tell user to try again
        break;
      }
      default: {
        // Toast for generic error
        break;
      }
    }
    return error;
  },
);

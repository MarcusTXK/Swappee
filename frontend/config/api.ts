import { create } from 'apisauce';
import { useSelector } from 'react-redux';
import { AxiosRequestConfig } from 'axios';
import { AppState } from 'redux-saga/interfaces';
import ApiUrlConfig from './envConfig';

enum StatusCode {
  Unauthorized = 401,
  InternalServerError = 500,
}

export const API = create({
  baseURL: ApiUrlConfig.API_URL,
});

export const ROUTES = {
  LOGIN: `/api/login/authenticate/`,
};

const AxiosRequest = (config: AxiosRequestConfig): AxiosRequestConfig => {
  try {
    const {
      user: { token },
    } = useSelector((state: AppState) => state);
    // eslint-disable-next-line
    if (token != null) config.headers.Authorization = `Bearer ${token}`;
    return config;
    // eslint-disable-next-line
  } catch (error: any) {
    throw new Error(error);
  }
};

API.axiosInstance.interceptors.request.use(AxiosRequest, (error) => Promise.reject(error));

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

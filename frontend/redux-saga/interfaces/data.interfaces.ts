export interface LoginData {
  token: string;
}

export interface Error {
  message?: string;
}

export interface AppState {
  error: null | Error;
  user: LoginData;

  isLoginLoading: boolean;
}

import * as data from './interfaces/data.interfaces';
import * as payloads from './interfaces/payloads.interfaces';
import * as actions from './interfaces/actions.interfaces';

const { actionTypes } = actions;

export function login(payload: payloads.LoginPayload): actions.Login {
  return {
    type: actionTypes.LOGIN,
    payload,
  };
}

export function loginSuccess(loginData: data.LoginData): actions.LoginSuccess {
  return {
    type: actionTypes.LOGIN_SUCCESS,
    data: loginData,
  };
}

export function loginFailed(error: data.Error): actions.LoginFailed {
  return {
    type: actionTypes.LOGIN_FAILED,
    error,
  };
}

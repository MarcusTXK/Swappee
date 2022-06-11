import { useRouter } from 'next/router';

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

export function logout(): actions.Logout {
  const Router = useRouter();
  Router.push('/');
  return {
    type: actionTypes.LOGOUT,
  };
}

export function getItemList(): actions.GetItemList {
  return {
    type: actionTypes.GET_ITEM_LIST,
  };
}

export function getItemListSuccess(itemListData: data.GetItemListData): actions.GetItemListSuccess {
  return {
    type: actionTypes.GET_ITEM_LIST_SUCCESS,
    data: itemListData,
  };
}

export function getItemListFailed(error: data.Error): actions.GetItemListFailed {
  return {
    type: actionTypes.GET_ITEM_LIST_FAILED,
    error,
  };
}

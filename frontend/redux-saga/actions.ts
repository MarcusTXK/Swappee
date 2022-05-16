import * as data from './interfaces/data.interfaces';
import * as payloads from './interfaces/payloads.interfaces';
import * as actions from './interfaces/actions.interfaces';

const { actionTypes } = actions;

export function login(payload: payloads.LoginPayload): actions.Login {
  console.log('Logging in');
  return {
    type: actionTypes.LOGIN,
    payload,
  };
}

export function loginSuccess(loginData: data.LoginData): actions.LoginSuccess {
  console.log('success!');
  return {
    type: actionTypes.LOGIN_SUCCESS,
    data: loginData,
  };
}

export function loginFailed(error: data.Error): actions.LoginFailed {
  console.log('failed!');
  return {
    type: actionTypes.LOGIN_FAILED,
    error,
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

export function getOtherUser(payload: payloads.GetOtherUserPayload): actions.GetOtherUser {
  console.log('Fetching other user');
  return {
    type: actionTypes.GET_OTHER_USER,
    payload,
  };
}

export function getOtherUserSuccess(otherUserData: data.GetOtherUserData): actions.GetOtherUserSuccess {
  console.log('Fetch user success');
  return {
    type: actionTypes.GET_OTHER_USER_SUCCESS,
    data: otherUserData,
  };
}

export function getOtherUserFailed(error: data.Error): actions.GetOtherUserFailed {
  console.log('Fetch user failed');
  return {
    type: actionTypes.GET_OTHER_USER_FAILED,
    error,
  };
}

export function getUser(payload: payloads.GetUserPayload): actions.GetUser {
  return {
    type: actionTypes.GET_USER,
    payload,
  };
}

export function getUserSuccess(userData: data.GetUserData): actions.GetUserSuccess {
  return {
    type: actionTypes.GET_USER_SUCCESS,
    data: userData,
  };
}

export function getUserFailed(error: data.Error): actions.GetUserFailed {
  return {
    type: actionTypes.GET_USER_FAILED,
    error,
  };
}

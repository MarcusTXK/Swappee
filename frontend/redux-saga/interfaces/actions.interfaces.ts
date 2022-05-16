/* eslint-disable no-unused-vars */
import * as data from './data.interfaces';
import * as payloads from './payloads.interfaces';

export enum actionTypes {
  LOGIN = 'LOGIN',
  LOGIN_SUCCESS = 'LOGIN_SUCCESS',
  LOGIN_FAILED = 'LOGIN_FAILED',

  GET_ITEM_LIST = 'GET_ITEM_LIST',
  GET_ITEM_LIST_SUCCESS = 'GET_ITEM_LIST_SUCCESS',
  GET_ITEM_LIST_FAILED = 'GET_ITEM_LIST_FAILED',

  GET_OTHER_USER = 'GET_OTHER_USER',
  GET_OTHER_USER_SUCCESS = 'GET_OTHER_USER_SUCCESS',
  GET_OTHER_USER_FAILED = 'GET_OTHER_USER_FAILED',

  GET_USER = 'GET_USER_DATA',
  GET_USER_SUCCESS = 'GET_USER_SUCCESS',
  GET_USER_FAILED = 'GET_USER_FAILED',
}

export interface Login {
  type: actionTypes.LOGIN;
  payload: payloads.LoginPayload;
}

export interface LoginSuccess {
  type: actionTypes.LOGIN_SUCCESS;
  data: data.LoginData;
}

export interface LoginFailed {
  type: actionTypes.LOGIN_FAILED;
  error: data.Error;
}

export interface GetItemList {
  type: actionTypes.GET_ITEM_LIST;
}

export interface GetItemListSuccess {
  type: actionTypes.GET_ITEM_LIST_SUCCESS;
  data: data.GetItemListData;
}

export interface GetItemListFailed {
  type: actionTypes.GET_ITEM_LIST_FAILED;
  error: data.Error;
}

export interface GetOtherUser {
  type: actionTypes.GET_OTHER_USER;
  payload: payloads.GetOtherUserPayload;
}

export interface GetOtherUserSuccess {
  type: actionTypes.GET_OTHER_USER_SUCCESS;
  data: data.GetOtherUserData;
}

export interface GetOtherUserFailed {
  type: actionTypes.GET_OTHER_USER_FAILED;
  error: data.Error;
}

export interface GetUser {
  type: actionTypes.GET_USER;
  payload: payloads.GetUserPayload;
}

export interface GetUserSuccess {
  type: actionTypes.GET_USER_SUCCESS;
  data: data.GetUserData;
}

export interface GetUserFailed {
  type: actionTypes.GET_USER_FAILED;
  error: data.Error;
}

export type Action =
  | Login
  | LoginSuccess
  | LoginFailed
  | GetItemList
  | GetItemListSuccess
  | GetItemListFailed
  | GetOtherUser
  | GetOtherUserSuccess
  | GetOtherUserFailed
  | GetUser
  | GetUserSuccess
  | GetUserFailed;

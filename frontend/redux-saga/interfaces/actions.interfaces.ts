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

export type Action = Login | LoginSuccess | LoginFailed | GetItemList | GetItemListSuccess | GetItemListFailed;

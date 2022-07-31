import { call, put, take, takeLatest } from 'redux-saga/effects';
import { ROUTES, API } from '../config/api';
import {
  loginSuccess,
  loginFailed,
  getItemListSuccess,
  getItemListFailed,
  getOtherUserSuccess,
  getOtherUserFailed,
  getUserSuccess,
  getUserFailed,
} from './actions';
import { actionTypes, Login, GetOtherUser, GetUser } from './interfaces';
import { GetOtherUserData } from './interfaces/data.interfaces';

function* loginSaga(action: Login) {
  const { data, ok } = yield call(() => API.post(ROUTES.LOGIN, action.payload));
  yield put(ok ? loginSuccess(data) : loginFailed(data));
}

function* getItemListSaga() {
  const { data, ok } = yield call(() => API.get(ROUTES.GET_ITEM_LIST));
  yield put(ok ? getItemListSuccess(data) : getItemListFailed(data));
}

function* getOtherUserSaga(action: GetOtherUser) {
  let data: GetOtherUserData = {
    message: '',
    isSuccess: false,
    data: { id: '', username: '', emailOnly: true, score: 0 },
  };
  let ok = true;
  //check if data already exists in AppState
  const otherUsersData = action.payload.otherUsersData;
  for (var i = 0; i < otherUsersData.length; i++) {
    if (otherUsersData[i].username == action.payload.username) {
      data = {
        message: 'Fetch other user success',
        isSuccess: true,
        data: otherUsersData[i],
      };
    }
  }
  //if data is not in AppState, make the API call
  if (!data.isSuccess) {
    ({ data, ok } = yield call(() => API.get(ROUTES.GET_OTHER_USERS + action.payload.username)));
  }

  yield put(ok ? getOtherUserSuccess(data) : getOtherUserFailed(data));
}

function* getUserSaga(action: GetUser) {
  const { data, ok } = yield call(() => API.get(ROUTES.GET_USER + action.payload.username));
  yield put(ok ? getUserSuccess(data) : getUserFailed(data));
}

function* rootSaga(): Generator {
  yield takeLatest(actionTypes.LOGIN, loginSaga);
  yield takeLatest(actionTypes.GET_ITEM_LIST, getItemListSaga);
  yield takeLatest(actionTypes.GET_OTHER_USER, getOtherUserSaga);
  yield takeLatest(actionTypes.GET_USER, getUserSaga);
}

export default rootSaga;

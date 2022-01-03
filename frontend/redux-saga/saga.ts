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
  getOtherUser,
} from './actions';
import { actionTypes, Login, GetOtherUserPayload } from './interfaces';

function* loginSaga(action: Login) {
  const { data, ok } = yield call(() => API.post(ROUTES.LOGIN, action.payload));
  yield put(ok ? loginSuccess(data) : loginFailed(data));
}

function* getItemListSaga() {
  const { data, ok } = yield call(() => API.get(ROUTES.GET_ITEM_LIST));
  yield put(ok ? getItemListSuccess(data) : getItemListFailed(data));
}

function* getOtherUsersSaga({ payload }: ReturnType<typeof getOtherUser>) {
  const { data, ok } = yield call(() => API.get(ROUTES.GET_OTHER_USERS + payload.username));
  yield put(ok ? getOtherUserSuccess(data) : getOtherUserFailed(data));
}

function* getUserSaga() {
  const { data, ok } = yield call(() => API.get(ROUTES.GET_USER));
  yield put(ok ? getUserSuccess(data) : getUserFailed(data));
}

function* rootSaga(): Generator {
  yield takeLatest(actionTypes.LOGIN, loginSaga);
  yield takeLatest(actionTypes.GET_ITEM_LIST, getItemListSaga);
  yield takeLatest(actionTypes.GET_OTHER_USERS, getOtherUsersSaga);
  yield takeLatest(actionTypes.GET_USER, getUserSaga);
}

export default rootSaga;

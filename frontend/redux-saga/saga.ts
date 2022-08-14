import { call, put, takeEvery, takeLatest } from 'redux-saga/effects';
import { ROUTES, API } from '../config/api';
import { loginSuccess, loginFailed, getItemListSuccess, getItemListFailed } from './actions';
import { actionTypes, Login } from './interfaces';

function* loginSaga(action: Login) {
  const { data, ok } = yield call(() => API.post(ROUTES.LOGIN, action.payload));
  yield put(ok ? loginSuccess(data) : loginFailed(data));
}

function* getItemListSaga() {
  const { data, ok } = yield call(() => API.get(ROUTES.GET_ITEM_LIST));
  yield put(ok ? getItemListSuccess(data) : getItemListFailed(data));
}

function* rootSaga(): Generator {
  yield takeLatest(actionTypes.LOGIN, loginSaga);
  yield takeLatest(actionTypes.GET_ITEM_LIST, getItemListSaga);
}

export default rootSaga;

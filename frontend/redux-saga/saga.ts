import { call, put, takeLatest } from 'redux-saga/effects';
import { ROUTES, API } from '../config/api';
import { loginSuccess, loginFailed } from './actions';
import { actionTypes, Login } from './interfaces';

function* loginSaga(action: Login) {
  const { data, ok } = yield call(() => API.post(ROUTES.LOGIN, action.payload));
  if (ok) {
    yield put(loginSuccess(data));
  } else {
    yield put(loginFailed(data));
  }
}

function* rootSaga(): Generator {
  yield takeLatest(actionTypes.LOGIN, loginSaga);
}

export default rootSaga;

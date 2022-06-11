import { HYDRATE } from 'next-redux-wrapper';
import { revokeData, setCookieAccessToken } from 'config/cookie';
import { AppState, Action, actionTypes, ItemData } from './interfaces';

const initialState: AppState = {
  error: null,
  user: {
    token: '',
  },
  items: [],

  isLoginLoading: false,
};

const reducer = (state = initialState, action: Action | { type: typeof HYDRATE; payload: AppState }): AppState => {
  switch (action.type) {
    case HYDRATE:
      return { ...state, ...action.payload };

    case actionTypes.LOGIN:
      return { ...state, isLoginLoading: true };

    case actionTypes.LOGIN_SUCCESS:
      setCookieAccessToken(action.data.token);
      return { ...state, user: action.data, isLoginLoading: false };

    case actionTypes.LOGIN_FAILED:
      return {
        ...state,
        ...{ error: action.error, isLoginLoading: false },
      };

    case actionTypes.LOGOUT:
      revokeData();
      return { ...state, ...initialState };

    case actionTypes.GET_ITEM_LIST_SUCCESS:
      return { ...state, items: action.data.data };

    case actionTypes.GET_ITEM_LIST_FAILED:
      return { ...state, error: action.error };

    default:
      return state;
  }
};
export default reducer;

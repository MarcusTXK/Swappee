import { HYDRATE } from 'next-redux-wrapper';
import { AppState, Action, actionTypes } from './interfaces';

const initialState: AppState = {
  error: null,
  user: {
    token: '',
  },

  isLoginLoading: false,
};

const reducer = (state = initialState, action: Action | { type: typeof HYDRATE; payload: AppState }): AppState => {
  switch (action.type) {
    case HYDRATE:
      return { ...state, ...action.payload };

    case actionTypes.LOGIN:
      return { ...state, isLoginLoading: true };

    case actionTypes.LOGIN_SUCCESS:
      return { ...state, user: action.data, isLoginLoading: false };

    case actionTypes.LOGIN_FAILED:
      return {
        ...state,
        ...{ error: action.error, isLoginLoading: false },
      };

    default:
      return state;
  }
};
export default reducer;

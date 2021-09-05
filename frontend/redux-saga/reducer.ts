import { HYDRATE } from 'next-redux-wrapper';
import { AppState, Action, actionTypes } from './interfaces';

const initialState: AppState = {
  error: null,
  user: {
    token: '',
  },
};

const reducer = (state = initialState, action: Action | { type: typeof HYDRATE; payload: AppState }): AppState => {
  switch (action.type) {
    case HYDRATE:
      return { ...state, ...action.payload };

    case actionTypes.LOGIN_SUCCESS:
      return { ...state, user: action.data };

    case actionTypes.LOGIN_FAILED:
      return {
        ...state,
        ...{ error: action.error },
      };

    default:
      return state;
  }
};
export default reducer;

import { HYDRATE } from 'next-redux-wrapper';
import { AppState, Action, actionTypes, ItemData } from './interfaces';

const initialState: AppState = {
  error: null,
  user: {
    token: '',
  },
  items: [],
  isLoginLoading: false,

  userData: {
    id: '',
    username: '',
    firstName: '',
    lastName: '',
    aboutMe: '',
    email: '',
    mobileNumber: '',
  },

  otherUserData: {
    id: '',
    username: '',
    emailOnly: false,
    score: 0,
  },
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

    case actionTypes.GET_ITEM_LIST_SUCCESS:
      return { ...state, items: action.data.data };

    case actionTypes.GET_ITEM_LIST_FAILED:
      return { ...state, error: action.error };

    case actionTypes.GET_OTHER_USERS_SUCCESS:
      return { ...state, otherUserData: action.data.data };

    case actionTypes.GET_OTHER_USERS_FAILED:
      return { ...state, error: action.error };

    case actionTypes.GET_USER_SUCCESS:
      return { ...state, userData: action.data.data };

    case actionTypes.GET_USER_FAILED:
      return { ...state, error: action.error };

    default:
      return state;
  }
};
export default reducer;

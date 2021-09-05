import { applyMiddleware, createStore, Middleware, Store, StoreEnhancer } from 'redux';
import createSagaMiddleware from 'redux-saga';
import { createWrapper } from 'next-redux-wrapper';

import rootReducer from 'redux-saga/reducer';
import rootSaga from 'redux-saga/saga';

const bindMiddleware = (middleware: Middleware[]): StoreEnhancer => {
  // if (process.env.NODE_ENV !== 'production') {
  //   const { composeWithDevTools } = require('redux-devtools-extension');
  //   return composeWithDevTools(applyMiddleware(...middleware));
  // }
  return applyMiddleware(...middleware);
};

export const makeStore = () => {
  const sagaMiddleware = createSagaMiddleware();
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const store: any = createStore(rootReducer, bindMiddleware([sagaMiddleware]));
  store.sagaTask = sagaMiddleware.run(rootSaga);
  return store;
};

export const wrapper = createWrapper(makeStore);

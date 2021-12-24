import { applyMiddleware, createStore, Middleware, StoreEnhancer, Store } from 'redux';
import createSagaMiddleware from 'redux-saga';
import { createWrapper } from 'next-redux-wrapper';
import { persistStore, persistReducer, Persistor } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // defaults to localStorage for web
import rootReducer from 'redux-saga/reducer';
import rootSaga from 'redux-saga/saga';

const bindMiddleware = (middleware: Middleware[]): StoreEnhancer => {
  return applyMiddleware(...middleware);
};

export interface StoreProperties {
  persistor?: Persistor;
}

export const makeStore = () => {
  const sagaMiddleware = createSagaMiddleware();
  let store: Store & StoreProperties;
  const isServer = typeof window === 'undefined';

  if (isServer) {
    // If it's on server side, create a store
    store = createStore(rootReducer, bindMiddleware([sagaMiddleware]));
    sagaMiddleware.run(rootSaga);
    return store;
  }

  const persistConfig = {
    key: 'root',
    storage,
  };
  const persistedReducer = persistReducer(persistConfig, rootReducer);
  store = createStore(persistedReducer, bindMiddleware([sagaMiddleware]));
  store.persistor = persistStore(store);
  sagaMiddleware.run(rootSaga);
  return store;
};

export const wrapper = createWrapper(makeStore);

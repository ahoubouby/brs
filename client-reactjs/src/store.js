import { applyMiddleware, compose, createStore } from 'redux';
import thunkMiddleware from 'redux-thunk';
//import rootReducer from './_reducers';

const middlewares = [thunkMiddleware];

if (process.env.NODE_ENV === 'development') {
    const { logger } = require('redux-logger');
    middlewares.push(logger);
}
export const store = createStore({},
    compose(
        applyMiddleware(...middlewares),
        window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(),
    ),
);
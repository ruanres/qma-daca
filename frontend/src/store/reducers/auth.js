import * as actionType from '../actions/actionTypes';
import { updateObj } from '../utils';


const initialState = {
    isLoading: false,
    error: null,
    token: null,
    message: null,
};

const authStart = (state, action) => {
    return updateObj(state, {
        isLoading: true,
        error: null
    });
};

const signinSuccess = (state, action) => {
    return updateObj(state, {
        isLoading: false,
        token: action.authData.accessToken
    });
};

const signupSuccess = (state, action) => {
    return updateObj(state, {
        isLoading: false,
        message: action.authData.message
    });
};

const authFail = (state, action) => {
    return updateObj(state, {
        isLoading: false,
        error: action.error
    });
};

const authLogout = (state, action) => {
    return updateObj(state, {
        token: null
    });
};


const reducer = (state= initialState, action) => {
    switch (action.type) {
        case actionType.AUTH_START: return authStart(state, action);
        case actionType.AUTH_FAIL: return authFail(state, action); 
        case actionType.AUTH_LOGOUT: return authLogout(state, action); 
        case actionType.SIGNIN_SUCCESS: return signinSuccess(state, action);
        case actionType.SIGNUP_SUCCESS: return signupSuccess(state, action);
        default: return state;
    }
};

export default reducer;
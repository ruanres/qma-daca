import * as actionTypes from './actionTypes';
import API from '../../config/api';

const SIGNIN_URL = '/auth/signin';
const SIGNUP_URL = '/auth/signup';

export const authStart = () => {
    return {
        type: actionTypes.AUTH_START
    };
};

export const signinSuccess = (authData) => {
    return {
        type: actionTypes.SIGNIN_SUCCESS,
        authData
    };
};

export const signupSuccess = (authData) => {
    return {
        type: actionTypes.SIGNUP_SUCCESS,
        authData
    };
};

export const authFail = (errorData) => {
    return {
        type: actionTypes.AUTH_FAIL,
        errorData
    };
};

export const logout = () => {
    return {
        type: actionTypes.AUTH_LOGOUT
    };
};

const checkAuthTimeout = (expirationTime) => {
    const MILLISECONDS_PER_SECOND = 1000;
    return dispatch => {
        setTimeout(() => {
            dispatch(logout());
        }, expirationTime * MILLISECONDS_PER_SECOND);
    };
};

export const signIn = (credentials) => {
    return dispatch => {
        dispatch(authStart());
    
        API.post(SIGNIN_URL, credentials)
            .then(res => {
                dispatch(signinSuccess(res.data));
                checkAuthTimeout(res.data.expiresIn)
            }).catch(error => {
                dispatch(authFail(error.response.data));
            });
    };
};

export const signUp = (signUpData) => {
    return dispatch => {
        dispatch(authStart());
    
        API.post(SIGNUP_URL, signUpData)
            .then(res => {
                dispatch(signupSuccess(res.data));
            }).catch(error => {
                dispatch(authFail(error.response.data));
            });
    };
};

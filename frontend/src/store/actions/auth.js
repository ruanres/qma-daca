import * as actionTypes from './actionTypes';
import API from '../../config/api';

const SIGNIN_URL = '/auth/signin';
const SIGNUP_URL = '/auth/signup';
const MILLISECONDS_PER_SECOND = 1000;

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
    localStorage.removeItem('token');
    localStorage.removeItem('expirationDate');
    return {
        type: actionTypes.AUTH_LOGOUT
    };
};

const checkAuthTimeout = (secondsUntilExpire) => {
    return dispatch => {
        setTimeout(() => {
            dispatch(logout());
        }, secondsUntilExpire * MILLISECONDS_PER_SECOND);
    };
};

export const signIn = (credentials) => {
    return dispatch => {
        dispatch(authStart());
    
        API.post(SIGNIN_URL, credentials)
            .then(res => {
                const expirationDate = calcExpirationDate(res.data.expiresIn);
                localStorage.setItem("token", res.data.accessToken);
                localStorage.setItem("expirationDate", expirationDate);
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

export const checkAuthState = () => {
    return dispatch => {
        const token = localStorage.getItem('token');
        if(!token) {
            dispatch(logout());
        } else {
            const expirationDate = new Date(localStorage.getItem('expirationDate'));
            if(expirationDate <= new Date()) {
                dispatch(logout());
            } else {
                dispatch(signinSuccess({accessToken: token}));
                const remainingTime = expirationDate.getTime() - new Date().getTime();
                checkAuthTimeout(remainingTime/MILLISECONDS_PER_SECOND);
            }
        }
    };
};

const calcExpirationDate = (expirationTime) => {
    return new Date(new Date().getTime() + expirationTime * MILLISECONDS_PER_SECOND);
};
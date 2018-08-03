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

export const authFail = (error) => {
    return {
        type: actionTypes.AUTH_FAIL,
        error
    };
};

export const signIn = (credentials) => {
    return auth(SIGNIN_URL, credentials, signinSuccess);
};

export const signUp = (signUpData) => {
    return auth(SIGNUP_URL, signUpData, signupSuccess);
};

const auth = (url, data, onSuccess) => {
    return dispatch => {
        dispatch(authStart());
    
        API.post(url, data)
            .then(res => {
                dispatch(onSuccess(res.data));
            }).catch(error => {
                dispatch(authFail(error));
            });
    };
};

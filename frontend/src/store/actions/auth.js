import * as actionTypes from './actionTypes';
import API from '../../config/api';

const SIGNIN_URL = '/auth/signin';
const SIGNUP_URL = '/auth/signup';

export const authStart = () => {
    return {
        type: actionTypes.AUTH_START
    };
};

export const authSuccess = (authData) => {
    return {
        type: actionTypes.AUTH_SUCCESS,
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
    return auth(SIGNIN_URL, credentials);
};

export const signUp = (signUpData) => {
    return auth(SIGNUP_URL, signUpData);
};

const auth = (url, data) => {
    return dispatch => {
        dispatch(authStart());
    
        API.post(url, data)
            .then(res => {
                dispatch(authSuccess(res.data));
            }).catch(error => {
                dispatch(authFail(error));
            });
    };
};

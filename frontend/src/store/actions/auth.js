import * as actionTypes from './actionTypes';
import API from '../../config/api';

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

export const signIn = (authData) => {
    return dispatch => {
        dispatch(authStart());

        API.post('/auth/signin', authData)
            .then(res => {
                dispatch(authSuccess());
            }).catch(err => {
                dispatch(authFail());
            });
    };
};
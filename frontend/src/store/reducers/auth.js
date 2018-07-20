import * as actionType from '../actions/actionTypes';
import { updateObj } from '../utils';


const initialState = {
    isAuthenticated: false,
    isLoading: false,
    hasFailed: false,
    token: null,
    data: null,
};

const authStart = (state, action) => {
    return updateObj(state, {
        isLoading: true,
        hasFailed: false
    });
};

const authSuccess = (state, action) => {
    return updateObj(state, {
        isLoading: false,
        data: action.authData
    });
};

const authFail = (state, action) => {
    return updateObj(state, {
        isLoading: false,
        hasFailed: true
    });
};

const reducer = (state= initialState, action) => {
    switch (action.type) {
        case actionType.AUTH_START: return authStart(state, action);
        case actionType.AUTH_SUCCESS: return authSuccess(state, action);
        case actionType.AUTH_FAIL: return authFail(state, action); 
        default: return state;
    }
};

export default reducer;
import * as actionType from '../actions/actionTypes';
import { updateObj } from '../utils';

const initialState = {
    tutors: [],
    isLoading: false
};

const tutorCreated = (state, action) => {
    return updateObj(state, {
        tutors: [...state.tutors, action.newTutor]
    });
}

const reducer = (state=initialState, action) => {
    switch(action.type) {
        case actionType.TUTOR_CREATED: return tutorCreated(state, action);
        default: return state;
    }
};

export default reducer;

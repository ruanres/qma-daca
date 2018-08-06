import * as actionTypes from './actionTypes';
import API from '../../config/api';

const TUTOR_URL = '/tutor';


const tutorCreated = (newTutor) => {
    return {
        type: actionTypes.TUTOR_CREATED,
        newTutor
    };
}; 

export const createTutor = (data) => {
    return dispatch => {
        API.post(TUTOR_URL, data)
            .then(res => {
                dispatch(tutorCreated(res.data));
            }).catch(error => {

            });
    };
};

import React from 'react';

import './Input.css';

const Input = (props) => {
    let inputElement = null;

    switch (props.inputtype) {
        case 'input':
            inputElement = <input className='InputElement' {...props} />;
            break;    
        case 'textarea':
            inputElement = <textarea {...props} />
            break;
        default:
            inputElement = <input {...props} />;
    }

    return (
        <div className='Input'>
            <label className='Label'>{props.label}</label>
            {inputElement}
        </div>
    );
};

export default Input;
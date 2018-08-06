import React, { Component } from 'react';
import { connect } from 'react-redux';

import './NewTutor.css';
import Form from '../../UI/Form';
import * as actions from '../../../store/actions'; 

class NewTutor extends Component {
    
    getForm() {
        const formFields = {
            subject: {
                label: 'Disciplina',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: ''
                },
                value: "",
                validation: {
                    required: true,
                },
                valid: false
            },
            proficiency: {
                label: "Nível de Conhecimento",
                elementType: 'select',
                elementConfig: {
                    options: [
                        {value: '1', displayValue: 'Iniciante'},
                        {value: '2', displayValue: 'Intermediário'},
                        {value: '3', displayValue: 'Avançado'},
                        {value: '4', displayValue: 'Especialista'},
                        {value: '5', displayValue: 'Mestre'}
                    ]
                },
                value: '1',
                validation: {
                    required: true,
                },
                valid: true
            }
        }

        return (
            <Form 
                fields={formFields} 
                btnLabel="Cadastrar"
                onSubmit={this.props.onTutorCreation}/>
        );
    }

    render() {
        return (
            <div>
                { this.getForm() }
            </div>
        );
    }
}

const dispatchToProps = dispatch => {
    return {
        onTutorCreation: (data) => dispatch(actions.createTutor(data))
    };
};

export default connect(null, dispatchToProps)(NewTutor);
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom';

import * as actions from '../../../store/actions';
import './SignUp.css';
import Form from '../../UI/Form';
import Spinner from '../../UI/Spinner';


class SignUp extends Component {
    state = { }

    getForm = () => {
        const formFields = {
            name: {
                label: 'Nome',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                validation: {
                    required: true,
                    notBlank: true
                },
                valid: false
            },
            email: {
                label: 'Email',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                validation: {
                    required: true,
                    notBlank: true
                },
                valid: false
            },
            registration: {
                label: 'Matrícula',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 9,
                    maxLength: 9
                },
                valid: false
            },
            courseCode: {
                label: 'Código do curso',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 5,
                    maxLength: 5
                },
                valid: false
            },
            phone: {
                label: 'Telefone',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                },
                value: '',
                validation: {},
                valid: false
            },
            password: {
                label: 'Senha',
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6
                },
                valid: false
            },
            confirmPassword: {
                label: 'Confirme a senha',
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                },
                value: '',
                validation: {
                    required: true,
                },
                valid: false
            }
        };

        return (
            <Form
                fields={formFields}
                btnLabel="Cadastrar"
                onSubmit={this.props.onSingUp}/>
        );
    }

    render() { 
        const form = this.props.signupSuccess ? (<Redirect to="/signin" />) : this.getForm();
        return ( 
            <div className="SignUp">
                {this.props.isLoading ? <Spinner/> : form }
            </div>
        );
    }
}

const stateToProps = state => {
    return {
        isLoading: state.auth.isLoading,
        signupSuccess: state.auth.registration.length > 0  
    };
};

const dispatchToProps = dispatch => {
    return {
        onSingUp: (data) => dispatch(actions.signUp(data))
    };
};

export default connect(stateToProps, dispatchToProps)(SignUp);

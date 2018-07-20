import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import * as actions from '../../../store/actions';

import './SignIn.css';

import Input from '../../UI/Input';


class SignIn extends Component {
    state = {
        signInForm: {
            registration: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Matrícula'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 9,
                    maxLength: 9
                },
                valid: false
            },
            password: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Senha'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6
                },
                valid: false
            }
        }
    }

    submitHandler = (event) => {
        event.preventDefault();
        const authData = Object.keys(this.state.signInForm).reduce((data, formElementId) => {
            data[formElementId] = this.state.signInForm[formElementId].value;
            return data;
        }, {});

        this.props.onSignIn(authData);
    }

    inputChangedHandler = (event, inputId) => {
        const updatedSignInForm = {...this.state.signInForm};
        const updatedFormElement = {...updatedSignInForm[inputId]};
        updatedFormElement.value = event.target.value;
        updatedFormElement.valid = this.checkValidity(updatedFormElement.value, updatedFormElement.validation);
        updatedSignInForm[inputId] = updatedFormElement;
        this.setState({signInForm: updatedSignInForm});
    }

    checkValidity = (value, rules) => {
        const isRequired = !rules.required || value.trim() !== '';
        const hasMinLength = !rules.minLength || value.length >= rules.minLength;
        const hasMaxLength = !rules.maxLength || value.length <= rules.maxLength;
        
        return isRequired && hasMinLength && hasMaxLength;
    }

    isFormValid = () => {
        const formElementsKeys = Object.keys(this.state.signInForm);
        const isValid = formElementsKeys.reduce((valid, elementKey) => {
            const inputElement = this.state.signInForm[elementKey];
            return valid && inputElement.valid;
        }, true);
        
        return isValid;
    }
    
    render() { 
        const formElements = Object.keys(this.state.signInForm).map(key => {
            return {
                id: key,
                config: this.state.signInForm[key]
            };
        });

        const inputs = formElements.map(element => (
            <Input {...element.config} key={element.id}
             changed={(event) => this.inputChangedHandler(event, element.id)}/>)
        );

        return ( 
            <div className='SignIn'>
                <form>
                    {inputs}
                </form>
                <button onClick={this.submitHandler} disabled={!this.isFormValid()}>
                    Login
                </button>
                <Link to='/signup'> 
                    <button>Cadastrar</button>
                </Link>
            </div>
        );
    }
}


const dispatchToProps = dispatch => {
    return {
        onSignIn: (authData) => dispatch(actions.signIn(authData))
    };
};
 
export default connect(null, dispatchToProps)(SignIn);

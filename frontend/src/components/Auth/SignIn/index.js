import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import * as actions from '../../../store/actions';

import './SignIn.css';
import Input from '../../UI/Input';
import Spinner from '../../UI/Spinner';


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
        const credentials = Object.keys(this.state.signInForm).reduce((data, formElementId) => {
            data[formElementId] = this.state.signInForm[formElementId].value;
            return data;
        }, {});

        this.props.onSignIn(credentials);
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

        const form = (
            <div>
                <form> {inputs} </form>
                <button onClick={this.submitHandler} disabled={!this.isFormValid()}>
                    Login
                </button>
                <Link to='/signup'> 
                    <button>Cadastrar</button>
                </Link>
            </div>
        );

        return ( 
            <div className='SignIn'>
               {this.props.isLoading ? <Spinner/> : form}
            </div>
        );
    }
}

const stateToProps = state => {
    return {
        isLoading: state.auth.isLoading
    };
};

const dispatchToProps = dispatch => {
    return {
        onSignIn: (credentials) => dispatch(actions.signIn(credentials))
    };
};
 
export default connect(stateToProps, dispatchToProps)(SignIn);

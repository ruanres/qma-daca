import React, { Component } from 'react';

import './SignUp.css';

import Input from '../../UI/Input';
import API from '../../../config/api';


class SignUp extends Component {
    state = {
        signUpForm: {
            name: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Nome'
                },
                value: '',
                validation: {
                    required: true,
                    notBlank: true
                },
                valid: false
            },
            email: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Email'
                },
                value: '',
                validation: {
                    required: true,
                    notBlank: true
                },
                valid: false
            },
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
            courseCode: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Código do curso'
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
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Telefone'
                },
                value: '',
                validation: {},
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
            },
            confirmPassword: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Confirme a senha'
                },
                value: '',
                validation: {
                    required: true,
                },
                valid: false
            }
        }
    }

    submitHandler = (event) => {
        event.preventDefault();
        const formData = Object.keys(this.state.signUpForm).reduce((data, formElementId) => {
            data[formElementId] = this.state.signUpForm[formElementId].value;
            return data;
        }, {});

        API.post('/auth/signup', formData)
            .then(res => {
                console.log(res.data);
            }).catch(err => {
                console.log(err.response.data.message);
            });
    }

    inputChangedHandler = (event, inputId) => {
        const updatedSignUpForm = {...this.state.signUpForm};
        const updatedFormElement = {...updatedSignUpForm[inputId]};
        updatedFormElement.value = event.target.value;
        updatedFormElement.valid = this.checkValidity(updatedFormElement.value, updatedFormElement.validation);
        updatedSignUpForm[inputId] = updatedFormElement;
        this.setState({signUpForm: updatedSignUpForm});
    }

    checkValidity = (value, rules) => {
        const isRequired = !rules.required || value.trim() !== '';
        const hasMinLength = !rules.minLength || value.length >= rules.minLength;
        const hasMaxLength = !rules.maxLength || value.length <= rules.maxLength;
        const isNotBlank = !rules.notBlank || value.trim().length > 0;
        
        return isRequired && hasMinLength && hasMaxLength && isNotBlank;
    }

    isFormValid = () => {
        const passwordsMatch = this.state.signUpForm.password.value === this.state.signUpForm.confirmPassword.value
        const formElementsKeys = Object.keys(this.state.signUpForm);
        const isValid = formElementsKeys.reduce((valid, elementKey) => {
            const inputElement = this.state.signUpForm[elementKey];
            return valid && inputElement.valid;
        }, true);
        return isValid && passwordsMatch;
    }


    render() { 
        const formElements = Object.keys(this.state.signUpForm).map(key => {
            return {
                id: key,
                config: this.state.signUpForm[key]
            };
        });

        const inputs = formElements.map(element => (
            <Input {...element.config} key={element.id}
             changed={(event) => this.inputChangedHandler(event, element.id)}/>)
        );

        return ( 
            <div className="SignUp">
                <form>
                    {inputs}
                </form>
                <button onClick={this.submitHandler} disabled={!this.isFormValid()}>
                    Enviar
                </button>
            </div>
        )
    }
}
 
export default SignUp;

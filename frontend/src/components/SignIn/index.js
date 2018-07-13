import React, { Component } from 'react';

import Input from '../UI/Input';
import API from '../../config/api';

class SignIn extends Component {
    state = {
        signInForm: {
            registration: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Sua matrícula'
                },
                value: ''
            },
            password: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Sua senha'
                },
                value: ''
            }
        }
    }

    submitHandler = (event) => {
        event.preventDefault();
        const formData = Object.keys(this.state.signInForm).reduce((data, formElementId) => {
            data[formElementId] = this.state.signInForm[formElementId].value;
            return data;
        }, {});

        API.post('/auth/signin', formData)
            .then(res => {
                console.log(res.data);
            }).catch(err => {
                console.log(err.response.data.message);
            });
    }

    inputChangedHandler = (event, inputId) => {
        const updatedSignInForm = {...this.state.signInForm};
        const updatedFormElement = {...updatedSignInForm[inputId]};
        updatedFormElement.value = event.target.value;
        updatedSignInForm[inputId] = updatedFormElement;
        this.setState({signInForm: updatedSignInForm});
    }
    
    render() { 
        const formElements = Object.keys(this.state.signInForm).map(key => {
            return {
                id: key,
                config: this.state.signInForm[key]
            };
        });

        return ( 
            <div>
                <form onSubmit={this.submitHandler}>
                    {formElements.map(element => (
                        <Input {...element.config} key={element.id}
                         changed={(event) => this.inputChangedHandler(event, element.id)}/>)
                    )}
                    <button type="submit">SingIn</button>
                </form>
            </div>
        );
    }
}
 
export default SignIn;

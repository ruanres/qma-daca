import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

import * as actions from '../../../store/actions';
import './SignIn.css';
import Form from '../../UI/Form';
import Spinner from '../../UI/Spinner';


class SignIn extends Component {
    state = { }

    getForm = () => {
        const formFields = {
            registration: {
                label: 'Matrícula',
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: ''
                },
                value: this.props.registration,
                validation: {
                    required: true,
                    minLength: 9,
                    maxLength: 9
                },
                valid: this.props.registration.length > 0
            },
            password: {
                label: 'Senha',
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: ''
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6
                },
                valid: false
            }
        };
        
        return (
            <div>
                <Form
                    fields={formFields}
                    btnLabel="Login"
                    onSubmit={this.props.onSignIn}/>            
                <Link to='/signup'>
                    <p>Clique aqui para se cadastrar</p> 
                </Link>
            </div>
        );
    }
    
    render() { 
        const form = this.props.isAuthenticated ? (<Redirect to="/" />) : this.getForm();
        return ( 
            <div className='SignIn'>
               { this.props.isLoading ? <Spinner/> : form }
            </div>
        );
    }
}

const stateToProps = state => {
    return {
        isLoading: state.auth.isLoading,
        registration: state.auth.registration,
        isAuthenticated: state.auth.token !== null
    };
};

const dispatchToProps = dispatch => {
    return {
        onSignIn: (credentials) => dispatch(actions.signIn(credentials))
    };
};
 
export default connect(stateToProps, dispatchToProps)(SignIn);

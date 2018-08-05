import React, { Component } from 'react';


import './NewTutor.css';
import Input from '../../UI/Input';

class NewTutor extends Component {
    state = {
        form: {
            subject: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Disciplina'
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
    };

    submitHandler = (event) => {
        event.preventDefault();
        const tutorData = Object.keys(this.state.form).reduce((data, formElementId) => {
            data[formElementId] = this.state.form[formElementId].value;
            return data;
        }, {});

        console.log(tutorData);
        
        // action
    }

    inputChangedHandler = (event, inputId) => {
        const updatedForm = {...this.state.form};
        const updatedFormElement = {...updatedForm[inputId]};
        updatedFormElement.value = event.target.value;
        updatedFormElement.valid = this.checkValidity(updatedFormElement.value, updatedFormElement.validation);
        updatedForm[inputId] = updatedFormElement;
        this.setState({form: updatedForm});
    }

    checkValidity = (value, rules) => {
        const isRequired = !rules.required || value.trim() !== '';
        return isRequired;
    }

    isFormValid = () => {
        const formElementsKeys = Object.keys(this.state.form);
        const isValid = formElementsKeys.reduce((valid, elementKey) => {
            const inputElement = this.state.form[elementKey];
            return valid && inputElement.valid;
        }, true);
        
        return isValid;
    }

    generateForm = () => {
        const formElements = Object.keys(this.state.form).map(key => {
            return {
                id: key,
                config: this.state.form[key]
            };
        });

        const inputs = formElements.map(element => (
            <Input {...element.config} key={element.id}
             changed={(event) => this.inputChangedHandler(event, element.id)}/>)
        );

        return (
            <div>
                <form> {inputs} </form>
                <button onClick={this.submitHandler} disabled={!this.isFormValid()}>
                    Cadastrar
                </button>
            </div>
        );
    }

    render() {

        const form = this.generateForm();
        return (
            <div className="NewTutor">
                { form }
            </div>
        );
    }
}

export default NewTutor;
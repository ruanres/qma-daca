import React, { Component } from 'react';


import './Form.css';
import Input from '../Input';

class Form extends Component {
    state = {
        form: { }
    };

    componentDidMount() {
        this.setState({form: { ...this.props.fields }});
    }

    submitHandler = (event) => {
        event.preventDefault();
        const formData = Object.keys(this.state.form).reduce((data, formElementId) => {
            data[formElementId] = this.state.form[formElementId].value;
            return data;
        }, {});

        this.props.onSubmit(formData);
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
        const hasMinLength = !rules.minLength || value.length >= rules.minLength;
        const hasMaxLength = !rules.maxLength || value.length <= rules.maxLength;
        const isNotBlank = !rules.notBlank || value.trim().length > 0;
        
        return isRequired && hasMinLength && hasMaxLength && isNotBlank;
    }

    isFormValid = () => {
        const formElementsKeys = Object.keys(this.state.form);
        const isValid = formElementsKeys.reduce((valid, elementKey) => {
            const inputElement = this.state.form[elementKey];
            return valid && inputElement.valid;
        }, true);
        // check password confirmation
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
                    { this.props.btnLabel }
                </button>
            </div>
        );
    }

    render() {
        return (
            <div className="Form">
                { this.generateForm() }
            </div>
        );
    }
}

export default Form;
import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';

import Header from './header';
import SignIn from '../signin';
import SignUp from '../signup';

class Main extends Component {

    render () {
        return (
            <div>
                <Header/>
                <Switch>
                    <Route path='/signin' exact component={ SignIn }/>
                    <Route path='/signup' exact component={ SignUp }/>
                </Switch>
            </div>
        );
    }
}

export default Main;

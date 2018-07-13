import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';

import './Main.css';
import Header from './Header';
import SignIn from '../SignIn';
import SignUp from '../SignUp';

class Main extends Component {

    render () {
        return (
            <div className="Main">
                <Header/>
                <div className="content">
                    <Switch>
                        <Route path='/signin' exact component={ SignIn }/>
                        <Route path='/signup' exact component={ SignUp }/>
                    </Switch>
                </div>
            </div>
        );
    }
}

export default Main;

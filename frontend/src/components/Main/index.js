import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';

import './Main.css';
import Header from './Header';
import SignIn from '../Auth/SignIn';
import SignUp from '../Auth/SignUp';

class Main extends Component {

    render () {
        return (
            <div className="Main">
                <Header/>
                <div className="content">
                    <Switch>
                        <Route path='/' exact render={() => <h4>Home</h4>}/>
                        <Route path='/signin' component={ SignIn }/>
                        <Route path='/signup' component={ SignUp }/>
                    </Switch>
                </div>
            </div>
        );
    }
}

export default Main;

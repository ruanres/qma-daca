import React, { Component } from 'react';
import { Switch, Route, withRouter } from 'react-router-dom';
import { connect } from 'react-redux';

import './Main.css';
import Header from './Header';
import SignIn from '../Auth/SignIn';
import SignUp from '../Auth/SignUp';
import Logout from '../Auth/Logout';
import * as actions from '../../store/actions';

class Main extends Component {

    componentDidMount() {
        this.props.onTryAutoSignin();
    }

    render () {
        return (
            <div className="Main">
                <Header isAuth={ this.props.isAuthenticated }/>
                <div className="content">
                    <Switch>
                        <Route path='/' exact render={() => <h4>Home</h4>}/>
                        <Route path='/signin' component={ SignIn }/>
                        <Route path='/signup' component={ SignUp }/>
                        <Route path='/logout' component={ Logout }/>
                    </Switch>
                </div>
            </div>
        );
    }
}

const stateToProps = state => {
    return {
        isAuthenticated: state.auth.token !== null
    };
};

const dispatchToProps = dispatch => {
    return {
      onTryAutoSignin: () => dispatch(actions.checkAuthState())
    };
  };

export default withRouter(connect(stateToProps, dispatchToProps)(Main));

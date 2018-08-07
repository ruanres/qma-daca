import React, { Component } from 'react';
import { Switch, Route, withRouter, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

import './Main.css';
import Header from './Header';
import SignIn from '../Auth/SignIn';
import SignUp from '../Auth/SignUp';
import Logout from '../Auth/Logout';
import NewTutor from '../Tutor/NewTutor';
import Home from '../Home';
import * as actions from '../../store/actions';

class Main extends Component {

    componentDidMount() {
        this.props.onTryAutoSignin();
    }

    render () {
        const routes = (
            <Switch>
                <Route path='/' exact component={ Home }/>
                <Route path='/signin' component={ SignIn }/>
                <Route path='/signup' component={ SignUp }/>
                <Redirect to="/" />
            </Switch>
        );

        const authRoutes = (
            <Switch>
                <Route path='/' exact render={() => <h4>Home</h4>}/>
                <Route path='/logout' component={ Logout }/>
                <Route path='/newtutor' component={ NewTutor }/>
                <Redirect to="/" />
            </Switch>
        );

        return (
            <div className="Main">
                <Header isAuth={ this.props.isAuthenticated }/>
                <div className="content">
                    { this.props.isAuthenticated? authRoutes : routes }
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

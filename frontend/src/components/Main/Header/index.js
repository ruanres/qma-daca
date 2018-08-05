import React from 'react';
import { NavLink } from 'react-router-dom';

import './Header.css';

const Header = (props) => {
    const login = (<NavLink to='/signin'>Login</NavLink>);
    const logout = (<NavLink to='/logout'>Logout</NavLink>);
    return (
        <div className='Header'>
            <nav>
                <ul>
                    <li><NavLink to='/' exact>Home</NavLink></li>
                    <li>{ props.isAuth ? logout : login }</li>
                </ul>
            </nav>
        </div>
    );
};


export default Header;

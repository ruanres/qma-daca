import React from 'react';
import { NavLink } from 'react-router-dom';

import './Header.css';

const Header = (props) => {
    const links = (
        <ul>
            <li><NavLink to='/' exact>Home</NavLink></li>
            <li><NavLink to='/signin'>Login</NavLink></li>
        </ul>
    );

    const authLinks = (
        <ul>
            <li><NavLink to='/' exact>Home</NavLink></li>
            <li><NavLink to='/newtutor' exact>Tutorar</NavLink></li>
            <li><NavLink to='/logout'>Logout</NavLink></li>
        </ul>
    );

    return (
        <div className='Header'>
            <nav>
                { props.isAuth ? authLinks : links }
            </nav>
        </div>
    );
};


export default Header;

import React from 'react';
import { NavLink } from 'react-router-dom';

import './Header.css';

const Header = (props) => {

    return (
        <div className='Header'>
            <nav>
                <ul>
                    <li><NavLink to='/' exact>Home</NavLink></li>
                    <li><NavLink to='/signin'>Login</NavLink></li>
                </ul>
            </nav>
        </div>
    );
};


export default Header;

import React from 'react';
import { NavLink } from 'react-router-dom';

import './Header.css';

const Header = (props) => {

    return (
        <div className='Header'>
            <nav>
                <ul>
                    <li><NavLink to='/signin'>SignIn</NavLink></li>
                    <li><NavLink to='/signup'>SignUp</NavLink></li>
                </ul>
            </nav>
        </div>
    );
};


export default Header;

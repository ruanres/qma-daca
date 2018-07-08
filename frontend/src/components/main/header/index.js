import React from 'react';
import { Link } from 'react-router-dom';

const Header = (props) => {

    return (
        <div>
            <nav>
                <ul>
                    <li><Link to='/signin'>SignIn</Link></li>
                    <li><Link to='/signup'>SignUp</Link></li>
                </ul>
            </nav>
        </div>
    );
};


export default Header;

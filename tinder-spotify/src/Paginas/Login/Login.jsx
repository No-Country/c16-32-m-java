import React from 'react';
import { Link } from 'react-router-dom';

import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

import log from '../../assets/log.png'
import google from '../../assets/google.png'

import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';
import Spotify from '../../Components/Iconos/Spotify/Spotify';

import log from '../../assets/log.png'
import signo from '../../assets/signo.png'
import AppStore from '../../assets/AppStore.png';
import GooglePlay from '../../assets/GooglePlay.png';
import './Login.css';

const Login = () => {
    const handleCloseClick = () => {
        window.location.href = '/';
    };

    return (
        <div className="login-container">
            <Link className="close-button" onClick={handleCloseClick}>
                <CloseIcon />
            </Link>
            <img src={log} alt="Logo" className="login-logo" />
            <h1>Inicia Sesión</h1>
            <button className="spotify-button">
                <Spotify />
                Continuar con Spotify
            </button>
            <Link to="/email-login" className="link">
                <button className="other-button">
                    <img src={signo} alt="Signo Logo" className="button-icon" />
                    Continuar con tu Email
                </button>
            </Link>
            <Link to="/crear-cuenta-con-celular" className="link">
                <button className="other-button">
                    <img src={signo} alt="Signo Logo" className="button-icon" />
                    Continuar con tu Número
                </button>
            </Link>
            <h2>¡Descarga la App!</h2>
            <div className="logos">
                <img src={AppStore} alt="App Store" />
                <img src={GooglePlay} alt="Google Play" />
            </div>
        </div>
    );
};

export default Login;
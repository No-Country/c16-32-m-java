import React from 'react';
import { Link } from 'react-router-dom';

import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon'

import log from '../../assets/log.png';
import google from '../../assets/google.png';
import signo from '../../assets/signo.png';
import AppStore from '../../assets/AppStore.png';
import GooglePlay from '../../assets/GooglePlay.png';
import './CrearCuenta.css';

const CrearCuenta = () => {

    return (
     <div className='background-ch'>
        <div className="login-container">
          <Link to="/" className="close-button">
              <CloseIcon />
          </Link>
          <img src={log} alt="Logo" className="login-logo" /> 
          <h1>Crea tu Cuenta</h1>
            <button className="google-button">
                <img src={google} alt="Google Logo" className="button-icon" /> 
                Continuar con Google
              </button>
          <Link to="/logear" className="link"> 
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
      </div>
    );
};
  
export default CrearCuenta;
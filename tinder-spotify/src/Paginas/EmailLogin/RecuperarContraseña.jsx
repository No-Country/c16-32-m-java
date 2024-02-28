import React from 'react';
import { Link } from 'react-router-dom';
import './RecuperarContraseña.css';

import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

const RecuperarContraseña = () => {

    const handleCloseClick = () => {
        window.location.href = '/';
    };
    return (
        <div className="recuperar-contraseña-container">
            <Link to="/" className="recuperClo" onClick={handleCloseClick}>
              <CloseIcon />
            </Link>
            <h1 className="recuperar-contraseña-title">Recuperar Contraseña</h1>
            <p className="recuperar-contraseña-description">A continuación ingresa el correo con que te registraste para enviarte un código con el cual puedes reestablecer tu contraseña.</p>
            <div className="input-container">
                <label className="recuperar-contraseña-label">Ingresa tu Email</label>
                <input className="recuperar-contraseña-input" type="email" placeholder="Correo Electrónico" />
            </div>
            <p className="recuperar-contraseña-resend">¿No recibiste el código? <span>Reenviar código</span></p>
            <Link to="/nueva-contraseña">
                <button className="recuperar-contraseña-button continuar">Continuar</button>
            </Link>
            <p className="recuperar-contraseña-terms">Al hacer clic en continuar, aceptas nuestros Términos y Condiciones. Conoce cómo procesamos tus datos en nuestra Política de privacidad y Políticas sobre cookies.</p>
        </div>
    );
};

export default RecuperarContraseña;
import React from 'react';
import { Link } from 'react-router-dom';
import './NuevaContraseña.css';
import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

const NuevaContraseña = () => {
    const handleCloseClick = () => {
        window.location.href = '/';
    };
    return (
        <div className="nueva-contraseña-container">
            <Link to="/" className="nuevaCo" onClick={handleCloseClick}>
              <CloseIcon />
            </Link>
            <h1 className="nueva-contraseña-title">Nueva Contraseña</h1>
            <div className="input-container">
                <label className="nueva-contraseña-label">Ingresa tu código</label>
                <input className="nueva-contraseña-input" type="text" placeholder="Código" />
            </div>
            <div className="input-container">
                <label className="nueva-contraseña-label">Nueva Contraseña</label>
                <input className="nueva-contraseña-input" type="password" placeholder="Nueva Contraseña" />
            </div>
            <div className="input-container">
                <label className="nueva-contraseña-label">Repetir Contraseña</label>
                <input className="nueva-contraseña-input" type="password" placeholder="Repetir Contraseña" />
            </div>
            <p className="nueva-contraseña-resend">¿No recibiste el código? <span>Reenviar código</span></p>
            <Link to="/home-privado" className="nueva-contraseña-button continuar">Continuar</Link>
            <p className="nueva-contraseña-terms">Al hacer clic en continuar, aceptas nuestros Términos y Condiciones. Conoce cómo procesamos tus datos en nuestra Política de privacidad y Políticas sobre cookies.</p>
        </div>
    );
};

export default NuevaContraseña;
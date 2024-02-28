import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios'; 
import './RecuperarContraseña.css';

import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

const RecuperarContraseña = () => {
    const [email, setEmail] = useState('');
    const [alert, setAlert] = useState('');

    const handleCloseClick = () => {
        window.location.href = '/';
    };

    const handleContinue = async () => {
        try {
            // Aquí realizas la solicitud HTTP con Axios
            const response = await axios.post('URL_DEL_SERVICIO', { email: email });
            console.log('Respuesta del servicio externo:', response.data);
            window.location.href = '/nueva-contraseña';
        } catch (error) {
            setAlert('Hubo un error al enviar el correo electrónico. Por favor, inténtalo de nuevo.');
        }
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
                <input
                    className="recuperar-contraseña-input"
                    type="email"
                    placeholder="Correo Electrónico"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            {alert && <p className="alert-message">{alert}</p>}
            <p className="recuperar-contraseña-resend">¿No recibiste el código? <span>Reenviar código</span></p>
            <button className="recuperar-contraseña-button continuar" onClick={handleContinue}>Continuar</button>
            <p className="recuperar-contraseña-terms">Al hacer clic en continuar, aceptas nuestros Términos y Condiciones. Conoce cómo procesamos tus datos en nuestra Política de privacidad y Políticas sobre cookies.</p>
        </div>
    );
};

export default RecuperarContraseña;
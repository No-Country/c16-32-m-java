import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios'; 
import './NuevaContraseña.css';
import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

const NuevaContraseña = () => {
    const [codigo, setCodigo] = useState('');
    const [nuevaContraseña, setNuevaContraseña] = useState('');
    const [repetirContraseña, setRepetirContraseña] = useState('');
    const [alert, setAlert] = useState('');

    const handleCloseClick = () => {
        window.location.href = '/';
    };

    const handleContinue = async () => {
        try {
            // Aquí realizas la solicitud HTTP con Axios
            const response = await axios.post('URL_DEL_SERVICIO', {
                codigo: codigo,
                nuevaContraseña: nuevaContraseña,
                repetirContraseña: repetirContraseña
            });
            console.log('Respuesta del servicio externo:', response.data);
            window.location.href = '/home-privado';
        } catch (error) {
            setAlert('Hubo un error al cambiar la contraseña. Por favor, inténtalo de nuevo.');
        }
    };

    return (
        <div className="nueva-contraseña-container">
            <Link to="/" className="nuevaCo" onClick={handleCloseClick}>
                <CloseIcon />
            </Link>
            <h1 className="nueva-contraseña-title">Nueva Contraseña</h1>
            <div className="input-container">
                <label className="nueva-contraseña-label">Ingresa tu código</label>
                <input
                    className="nueva-contraseña-input"
                    type="text"
                    placeholder="Código"
                    value={codigo}
                    onChange={(e) => setCodigo(e.target.value)}
                />
            </div>
            <div className="input-container">
                <label className="nueva-contraseña-label">Nueva Contraseña</label>
                <input
                    className="nueva-contraseña-input"
                    type="password"
                    placeholder="Nueva Contraseña"
                    value={nuevaContraseña}
                    onChange={(e) => setNuevaContraseña(e.target.value)}
                />
            </div>
            <div className="input-container">
                <label className="nueva-contraseña-label">Repetir Contraseña</label>
                <input
                    className="nueva-contraseña-input"
                    type="password"
                    placeholder="Repetir Contraseña"
                    value={repetirContraseña}
                    onChange={(e) => setRepetirContraseña(e.target.value)}
                />
            </div>
            {alert && <p className="alert-message">{alert}</p>}
            <p className="nueva-contraseña-resend">¿No recibiste el código? <span>Reenviar código</span></p>
            <button className="nueva-contraseña-button continuar" onClick={handleContinue}>Continuar</button>
            <p className="nueva-contraseña-terms">Al hacer clic en continuar, aceptas nuestros Términos y Condiciones. Conoce cómo procesamos tus datos en nuestra Política de privacidad y Políticas sobre cookies.</p>
        </div>
    );
};

export default NuevaContraseña;
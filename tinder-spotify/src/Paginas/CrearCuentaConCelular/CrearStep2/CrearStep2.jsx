import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import CloseIcon from '../../../Components/Iconos/CloseIcon/CloseIcon';
import log from '../../../assets/log.png';
import './CrearStep2.css';

const CrearStep2 = ({ handleContinue, handleClose }) => {
  const [codigo, setCodigo] = useState('');
  const [alert, setAlert] = useState('');
  const navigate = useNavigate();

  const handleContinueClick = () => {
    if (codigo.trim() !== '') {
      navigate('/home-privado');
    } else {
      setAlert('Por favor, ingresa tu código.');
    }
  };

  return (
    <div className="crear-step2-container">
      <Link to="/" className="Step2" onClick={handleClose}>
          <CloseIcon />
      </Link>
      <img src={log} alt="Logo" className="crear-step2-logo" />
      <h1 className="crear-step2-title">Crear cuenta</h1>
      <h2 className="crear-step2-subtitle">Ingresa tu código</h2>
      <input
        type="text"
        placeholder="Ingresa tu código aquí"
        className="crear-step2-input"
        value={codigo}
        onChange={(e) => setCodigo(e.target.value)}
      />
      {alert && <p className="crear-step2-alert">{alert}</p>}
      <p className="crear-step2-paragraph">
        <span className="crear-step2-span">Cambiar número de teléfono</span> - 
        <span className="crear-step2-span">Reenviar</span>
      </p>
      <button onClick={handleContinueClick} className="crear-step2-button contiStep2">Continuar</button>
    </div>
  );
};

export default CrearStep2;
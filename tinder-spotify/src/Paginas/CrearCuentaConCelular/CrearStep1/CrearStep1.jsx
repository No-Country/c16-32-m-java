import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import CloseIcon from '../../../Components/Iconos/CloseIcon/CloseIcon';
import log from '../../../assets/log.png';
import './CrearStep1.css';
import useInputValidation from '../useInputValidation';

const CrearStep1 = ({ handleContinue, handleClose }) => {
  const { alert, validateInputs } = useInputValidation(); 
  const [pais, setPais] = useState('');
  const [numero, setNumero] = useState('');

  const handleContinueClick = () => {
    const inputs = [pais, numero];
    const isValid = validateInputs(inputs);
    if (isValid) {
      handleContinue();
    }
  };

  return (
    <div className="crear-step1-container">
      <Link to="/" className="Step1" onClick={handleClose}>
          <CloseIcon />
      </Link>
      <img src={log} alt="Logo" className="crear-step1-logo" />
      <h1 className="crear-step1-title">Crear cuenta</h1>
      <h2 className="crear-step1-subtitle">Ingresa tu número de celular</h2>
      <div className='crear-step1-inputs'>
      <div className="crear-step1-input-group">
        <label htmlFor="pais" className="crear-step1-pais">País:</label>
        <input type="text" id="pais" className="crear-step1-input-pais" value={pais} onChange={(e) => setPais(e.target.value)} />
      </div>
      <div className="crear-step1-input-group">
        <label htmlFor="numero" className="crear-step1-numero">Número de celular:</label>
        <input type="text" id="numero" className="crear-step1-input-numero" value={numero} onChange={(e) => setNumero(e.target.value)} />
      </div>
      </div>
      {alert && <p className="alert-message">{alert}</p>}
      <p className="crear-step1-paragraph">Al hacer clic en continuar, te enviaremos un mensaje de texto con un código de verificación. Se pueden aplicar tarifas de mensajes y datos. El número verificado se puede usar para iniciar sesión.</p>
      <button onClick={handleContinueClick} className="crear-step1-button contiStep">Continuar</button>
      
    </div>
  );
};

export default CrearStep1;
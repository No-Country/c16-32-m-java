import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'; 
import './EmailLogin.css'; 

import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

const EmailLogin = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [alert, setAlert] = useState('');
  const navigate = useNavigate(); 

  const handleContinue = () => {
    if (email === '' || password === '') {
      setAlert('Por favor, completa todos los campos.');
    } else {
      setAlert('');
      navigate('/home-privado'); 
    }
  };

  return (
    <form className="email-login-container">
      <Link to="/" className="EmailLog">
          <CloseIcon />
      </Link>
      <h1 className="email-login-title">Iniciar Sesión</h1>
      <div className="input-container">
        <label className="email-login-label">Email</label>
        <input 
          className="email-login-input" 
          type="email" 
          placeholder="Email" 
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <label className="email-login-label">Password</label>
        <input 
          className="email-login-input" 
          type="password" 
          placeholder="Password" 
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <Link to="/recuperar-contraseña" className="email-login-link">¿Olvidaste tu contraseña?</Link>
      <button 
        type="button" 
        className="email-login-button continuarEm" 
        onClick={handleContinue}
      >
        Continuar
      </button>
      {alert && <p className="alert-message">{alert}</p>}
      <p className="email-login-paragraph">
        Al hacer clic en continuar, aceptas nuestros Términos y Condiciones.
        Conoce cómo procesamos tus datos en nuestra 
        Política de privacidad
        y Políticas sobre cookies.
      </p>
    </form>
  );
};

export default EmailLogin;
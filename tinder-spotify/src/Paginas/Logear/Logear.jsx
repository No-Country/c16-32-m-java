import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useFormValidation from '../../hook/useFormValidation';
import LogoP from '../../Components/Iconos/LogoP/LogoP';
import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';
import Volver from '../../Components/Iconos/Volver/Volver';
import PostTitle from '../../Components/Paragraph/PostTitle/PostTitle';
import './Logear.css';

const Logear = () => {
  const { email, setEmail, password, setPassword, repeatPassword, setRepeatPassword } = useFormValidation();
  const navigate = useNavigate();

  const handleContinue = () => {
    if (email.trim() === '' || password.trim() === '' || repeatPassword.trim() === '') {
      setAlert('Por favor, completa todos los campos.');
    } else {
      if (password !== repeatPassword) {
        setAlert('Las contraseñas no coinciden.');
      } else {
        navigate('/codigo');
      }
    }
  };

  return (
    <div className="container logear-container">
            <div className="closeLog-icon">
        <Link to="/">
          <CloseIcon className="close-icon" />
        </Link>
      </div>
      <div className="logo-container-Log">
        <LogoP className="logo" />
      </div>
      <PostTitle className="post-titleLo">Crear cuenta</PostTitle>
      <div className="form-container">
        <input 
          type="email" 
          placeholder="Correo electrónico" 
          className="email-input" 
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input 
          type="password" 
          placeholder="Contraseña" 
          className="password-input" 
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input 
          type="password" 
          placeholder="Repetir contraseña" 
          className="repeat-password-input" 
          value={repeatPassword}
          onChange={(e) => setRepeatPassword(e.target.value)}
        />
        <button className="continue-codi" onClick={handleContinue}>Continuar</button>     
      </div>
      {error && <p className="alert-message">{error}</p>}
      <div className="back-arrow">
        <Link to="/crear-cuenta" className="link">
          {/* <Volver className="back-arrow-icon" /> */}
        </Link>
      </div>
    </div>
  );
};

export default Logear;
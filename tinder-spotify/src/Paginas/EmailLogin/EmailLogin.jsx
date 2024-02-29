import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios'; 
import './EmailLogin.css'; 
import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';

const EmailLogin = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [alert, setAlert] = useState('');
  const navigate = useNavigate();

  const handleContinue = async () => {
    try {
      const response = await axios.post("http://localhost:8080/users/login", {
        email: email,
        password: password
      });
      console.log('Respuesta del servicio externo:', response.data);
      localStorage.setItem("token-ChatBeat", response.data.token);
      localStorage.setItem("userId", response.data.userId);
      navigate('/home-privado');
    } catch (error) {
      setAlert('Hubo un error al iniciar sesión. Por favor, inténtalo de nuevo.');
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
        {alert && <p className="alert">{alert}</p>}
      </div>
      <Link to="/recuperar-contraseña" className="email-login-link">¿Olvidaste tu contraseña?</Link>
      <button 
        type="button" 
        className="email-login-button continuarEm" 
        onClick={handleContinue}
      >
        Continuar
      </button>
      
      <p className="email-login-paragraph">
        Al hacer clic en continuar, aceptas nuestros Términos y Condiciones.
        Conoce cómo procesamos tus datos en nuestra 
        <Link to="/policy" className="email-login-link"> Política de privacidad
        y Políticas sobre cookies. </Link>
      </p>
    </form>
  );
};

export default EmailLogin;
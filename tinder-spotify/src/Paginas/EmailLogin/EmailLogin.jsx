import React, { useState } from 'react';
import { Link } from 'react-router-dom'; 
import axios from 'axios';
import "./EmailLogin.css";

const EmailLogin = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    const user = {
      "password" : password,
      "email" : email,
    };
    console.log(user.email, user.password);
    axios.post("http://localhost:8080/users/login", user)
      .then((response) => {
        console.log(response.data);
        localStorage.setItem("token-ChatBeat", response.data.token)
      })
      .catch((error) => {
        console.error('Error al iniciar sesión:', error);
      });
  };

  return (
    <div className="email-login-container">
      <h1 className="email-login-title">Iniciar Sesión</h1>
      <div className="input-container">
        <label className="email-login-label">Email</label>
        <input className="email-login-input" type="email" placeholder="Email"
        value={email} onChange={(e) => setEmail(e.target.value)} />
        <label className="email-login-label">Password</label>
        <input className="email-login-input" type="password" 
        placeholder="Password" value={password} 
        onChange={(e) => setPassword(e.target.value)} />
      </div>
      <Link to="/recuperar-contraseña" className="email-login-link">¿Olvidaste tu contraseña?</Link>
      <Link to="/home-privado" className="email-login-button continuar" onClick={handleLogin}>Continuar</Link>
      <p className="email-login-paragraph">Al hacer clic en continuar, aceptas nuestros Términos y Condiciones. Conoce cómo procesamos tus datos en nuestra <Link to="/policy" className="email-login-link"> Política de privacidad y Políticas sobre cookies.</Link> </p>
    </div>
  );
};

export default EmailLogin;
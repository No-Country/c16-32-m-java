import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import LogoP from '../../../Components/Iconos/LogoP/LogoP';
import CloseIcon from '../../../Components/Iconos/CloseIcon/CloseIcon';
import PostTitle from '../../../Components/Paragraph/PostTitle/PostTitle';
import Parrafo from '../../../Components/Paragraph/Parrafo/Parrafo';
import './Codigo.css';

const Codigo = () => {
  const [codigo, setCodigo] = useState('');
  const [alert, setAlert] = useState('');
  const [timeLeft, setTimeLeft] = useState(80);
  const navigate = useNavigate();

  useEffect(() => {
    // Función para realizar la llamada al servicio externo
    const verifyCode = async () => {
      try {
        // Realizar la llamada al servicio externo usando Axios
        const response = await axios.post('URL_DEL_SERVICIO', { codigo });
        console.log('Respuesta del servicio externo:', response.data);

        // Redirigir al usuario a otra página si es necesario
        navigate('/home-privado');
      } catch (error) {
        console.error('Error al llamar al servicio externo:', error);
        setAlert('Error al verificar el código. Por favor, inténtalo de nuevo.');
      }
    };
    verifyCode();
  }, [codigo, navigate]); 

  useEffect(() => {
    if (timeLeft > 0) {
      const timer = setInterval(() => {
        setTimeLeft(prevTime => prevTime - 1);
      }, 1000);
      return () => clearInterval(timer);
    }
  }, [timeLeft]);

  const handleContinue = () => {
    if (codigo.trim() !== '') {
    } else {
      setAlert('Por favor, ingresa el código de verificación.');
    }
  };

  const handleResendCode = () => {
    setTimeLeft(80);
  };

  const minutes = Math.floor(timeLeft / 60);
  const seconds = timeLeft % 60;
  const formattedTime = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  return (
    <div className="codigo-container">
      <div className="closeCo-icon-container">
        <Link to="/">
          <CloseIcon className="close-icon" />
        </Link>
      </div>
      <div className="logo-container">
        <LogoP className="logo" />
      </div>
      <div className="post-title-container">
        <PostTitle className="post-title">Crear cuenta</PostTitle>
      </div>
      <Parrafo className="verification-text">Introduce el código de verificación que has recibido en tu correo.</Parrafo>
      <input
        type="password"
        placeholder="Código de verificación"
        className="verification-input"
        value={codigo}
        onChange={(e) => setCodigo(e.target.value)}
      />
      {alert && <Parrafo className="alert-message">{alert}</Parrafo>}
      <div className="change-email-reenviar-container">
        <Parrafo className="change-email-text">Cambiar correo electrónico</Parrafo>
        <div className="reenviar-container">
          <Parrafo className="resend-text" onClick={handleResendCode}>Reenviar</Parrafo>
          <Parrafo className="timer-text">{formattedTime} t</Parrafo>
        </div>
      </div>
      <button onClick={handleContinue} className="continuar-button">Continuar</button>
    </div>
  );
}

export default Codigo;
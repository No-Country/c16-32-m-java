import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios'; // Importar Axios
import useFormValidation from '../../hook/useFormValidation';
import LogoP from '../../Components/Iconos/LogoP/LogoP';
import CloseIcon from '../../Components/Iconos/CloseIcon/CloseIcon';
import Volver from '../../Components/Iconos/Volver/Volver';
import PostTitle from '../../Components/Paragraph/PostTitle/PostTitle';
import './Logear.css';

const Logear = () => {
  const { email, setEmail, password, setPassword, repeatPassword, setRepeatPassword, alert, handleContinue } = useFormValidation();
  const navigate = useNavigate();

  const [error, setError] = useState(null);

  const handleSubmit = async () => {
    try {
      // Aquí realizas la llamada a tu servicio externo usando Axios
      const response = await axios.post('URL_DEL_SERVICIO', {
        email: email,
        password: password,
        repeatPassword: repeatPassword
      });
      
      console.log('Respuesta del servicio externo:', response.data);
      
      navigate('/home-privado');
    } catch (error) {
      setError(error.message);
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
<<<<<<< HEAD
        {alert && <p className="alert-message">{alert}</p>}
        <button className="continue-codi" onClick={handleContinue}>Continuar</button>     
      </div>
      
=======
        <button className="continue-codi" onClick={handleSubmit}>Continuar</button>     
      </div>
      {error && <p className="alert-message">{error}</p>}
>>>>>>> 362298dd725179e860d39cfdb98045d90e638caa
      <div className="back-arrow">
        <Link to="/crear-cuenta" className="link">
          {/* <Volver className="back-arrow-icon" /> */}
        </Link>
      </div>
    </div>
  );
};

export default Logear;
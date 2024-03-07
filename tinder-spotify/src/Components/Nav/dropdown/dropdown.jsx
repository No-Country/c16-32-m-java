import React from 'react';
import { useNavigate } from 'react-router-dom';
import './dropdown.css';
import log from '../../../assets/log.png';
import foto2 from '../../../assets/foto2.png';
import configuracion from '../../../assets/configuracion.png';
import chat from '../../../assets/chat.png';
import '../../StyleVariables/Color.css'


const dropdown = ({ onCloseDropdown }) => {
    
    const navigate = useNavigate();

    const handleCloseButtonClick = () => {
        onCloseDropdown();
    };
    
  const handleChatClick = () => {
    navigate('/chat');
  };
    
return (
    <>
        <div className="dropdown-container">
            <div className="header">
                <img  className="img-logo" />
                <h2>ChatBeat</h2>
                <button className="close-button" onClick={handleCloseButtonClick}>x</button>
            </div>
            <div className="profile">
                <img src={foto2} alt="Profile" />
                <p>Alberto Perez</p>
            </div>
            <div className="menu-item" onClick={handleChatClick}>
                <img src={chat} alt="Icon3" />
                <p>Chat</p>
            </div>
            <div className="menu-item">
                <img src={configuracion} alt="Icon1" />
                <p>Configuración</p>
            </div>
            <button className="logout-button">Cerrar Sesión</button>
        </div>
    </>
  )
}

export default dropdown;
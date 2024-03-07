import React from 'react';
import { useNavigate, Link } from 'react-router-dom';

import './dropdown.css';
import log from '../../../assets/log.png';
import foto2 from '../../../assets/foto2.png';
import configuracion from '../../../assets/configuracion.png';
import chat from '../../../assets/chat.png';
import '../../StyleVariables/Color.css'
import SliderButton from '../../SliderButton/Slider'

const dropdown = ({ onCloseDropdown ,onChange }) => {
    const handleChange = (isChecked) => {
        onChange(isChecked);
      };
    
    const username = localStorage.getItem("username");

    const navigate = useNavigate();

    const handleCloseButtonClick = () => {
        onCloseDropdown();
    };

    const handleChatClick = () => {
        navigate('/chat');
    };

    const handleSignOut = () => {
        localStorage.clear();
        navigate('/');
    };

    return (
        <>
            <div className="dropdown-container">
                <div className="header">
                    <img className="img-logo" />
                    <Link to="/home-privado" className="icono-cont">
                    <h2>ChatBeat</h2>
                    </Link>
                    
                    <button className="close-button" onClick={handleCloseButtonClick}>x</button>
                </div>
                <Link to="/editar-perfil" className="profile my-5" style={{ marginTop: '50px' }} >
                    <img src={foto2} alt="Profile" />
                    <p >{username}</p>
                </Link>

                <div className="menu-item mx-3" onClick={handleChatClick}>
                    <img src={chat} alt="Icon3" />
                    <p>Chat</p>
                </div>
                <div className="menu-slider m-2">
                <p className='mx-2'>Cambiar Tema</p>   
                <SliderButton className="slider-con mx-2"  onChange={handleChange}/>
                </div>
                {/* <div className="menu-item">
                    <img src={configuracion} alt="Icon1" />
                    <p>Configuración</p>
                </div> */}
                <button className="logout-button" onClick={handleSignOut}>Cerrar Sesión</button>
            </div>
        </>
    )
}

export default dropdown;
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './SideNav.css';

import Dropdown from '../../../Components/Nav/dropdown/dropdown'; 

import log from '../../../assets/log.png';
import foto from '../../../assets/foto.png';
import configuracionb from '../../../assets/configuracionb.png';
import corazonb from '../../../assets/corazonb.png';
import chatb from '../../../assets/chatb.png';
import musicb from '../../../assets/musicb.png';
import homeb from '../../../assets/homeb.png';
import salidab from '../../../assets/salidab.png';

const SideNav = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false); 

  const handleLogoClick = () => {
    setIsDropdownOpen(true); 
  };

  const handleCloseDropdown = () => {
    setIsDropdownOpen(false); 
  };

  return (
    <div className="SideNav">
      <div className="logo logo-container"  onClick={handleLogoClick} >
        <img className="logo-image" src={log} alt="Logo" style={{ filter: 'brightness(0) invert(1)' }} />
      </div>
      <Link to="/editar-perfil" className="profile-picture profile-picture-container" style={{ marginTop: '50px' }} >
        <img className="profile-image" src={foto} alt="Profile" />
      </Link>
      <div className="icons">
        <div className="icon">
          <button className="button">
            <img className="logo-image" src={configuracionb} alt="Logo configuracion" />
          </button>
        </div>
        <div className="icon">
          <button className="button">
            <img className="logo-image" src={corazonb} alt="Logo corazon" />
          </button>
        </div>
        <div className="icon">
          <button className="button">
            <img className="logo-image" src={chatb} alt="Logo chat" />
          </button>
        </div>
        <div className="icon">
          <button className="button">
            <img className="logo-image" src={musicb} alt="Logo music" />
          </button>
        </div>
        <div className="icon">
          <button className="button">
            <img className="logo-image" src={homeb} alt="Logo inicio" />
          </button>
        </div>
        <div className="icon">
          <button className="button">
            <img className="logo-image" src={salidab} alt="Logo salir" />
          </button>
        </div>
      </div>
      {isDropdownOpen && <Dropdown onCloseDropdown={handleCloseDropdown} />}
    </div>
  );
};

export default SideNav;
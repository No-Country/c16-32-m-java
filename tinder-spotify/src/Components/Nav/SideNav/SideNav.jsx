import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './SideNav.css';
import { useNavigate } from 'react-router-dom';
import Dropdown from '../../../Components/Nav/dropdown/dropdown'; 

import log from '../../../assets/fi-br-menu-burgermenu.png';
import foto2 from '../../../assets/foto2.png';
import configuracionb from '../../../assets/configuracionb.png';
import corazonb from '../../../assets/corazonb.png';
import chatb from '../../../assets/chatb.png';
import musicb from '../../../assets/musicb.png';
import homeb from '../../../assets/homeb.png';
import salidab from '../../../assets/salidab.png';

const SideNav = ({onChange}) => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false); 
  const navigate = useNavigate();

  const handleLogoClick = () => {
    setIsDropdownOpen(true); 
  };

  const handleCloseDropdown = () => {
    setIsDropdownOpen(false); 
  };

  const handleChatClick = () => {
    navigate('/chat');
  };

  return (
    <div className="SideNav">
      <div className="logo logo-container"  onClick={handleLogoClick} >
        <img className="logo-image" src={log} alt="Logo" style={{ filter: 'brightness(0) invert(1)' }} />
      </div>
      <Link to="/editar-perfil" className="profile-picture profile-picture-container" style={{ marginTop: '50px' }} >
        <img className="profile-image" src={foto2} alt="Profile" />
      </Link>
      <div className="icons">
      <div className="icon">
          <button className="button" onClick={handleChatClick} >
            <img className="icon-image" src={chatb} alt="Logo chat" />
          </button>
        </div>
        
        {/* <div className="icon">
          <button className="button">
            <img className="icon-image" src={configuracionb} alt="Logo configuracion" />
          </button>
        </div> */}
      </div>
      {isDropdownOpen && <Dropdown onCloseDropdown={handleCloseDropdown} onChange={onChange} />}
    </div>
  );
};

export default SideNav;
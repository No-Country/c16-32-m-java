import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import log from '../../assets/log.png';
import BotonPin from '../Button/BotonPrincipal/BotonPin';
import AboutUsModal from '../AboutUsModal/AboutUsModal';
import './Nav.css';

const Navbar = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    
    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };
  
    return (
      <div className="navbar">
        <div className="left-section">
          <img src={log} alt="Logo" className="logo" />
          <span>ChatBeat</span>
        </div>
        <div className="middle-section">
          <span onClick={openModal} style={{ cursor: 'pointer' }}>Sobre nosotros</span>
          {isModalOpen && <AboutUsModal onClose={closeModal} />}
        </div>
        <div className="right-section">
          <Link to="/login">
            <BotonPin texto="Iniciar Sesión" />
          </Link>
        </div>
      </div>
    );
  };
  
  export default Navbar;
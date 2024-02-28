import React from 'react';
import './Perfil.css';

import foto from '../../../assets/foto.png';

const Perfil = () => {
  return (
    <div className="perfil-seccion">
      <div className="perfil-foto">
        <img src={foto} alt="Perfil" />
      </div>
      <div className="perfil-info">
        <p>Alberto Perez</p>
        <p className="edad">21</p>
      </div>
    </div>
  );
};

export default Perfil;
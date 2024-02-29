import React from 'react';
import './Perfil.css';

import foto2 from '../../../assets/foto2.png';

const Perfil = () => {
  return (
    <div className="perfil-seccion">
      <div className="perfil-foto">
        <img src={foto2} alt="Perfil" />
      </div>
      <div className="perfil-info">
        <p>Sara Castro</p>
        <p className="edad">21</p>
      </div>
    </div>
  );
};

export default Perfil;
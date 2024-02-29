import React from 'react';
import './GeneroHome.css';
import Ubicacion from '../../../assets/Ubicacion.png';

const GeneroHome = () => {
  return (
    <div className="genero-home-container">
      <div>
        <p><strong>Genero:</strong> Masculino</p>
        <p><strong>Pronombre:</strong> El</p>
      </div>
      <div className="ubicacion-container">
        <img src={Ubicacion} alt="Ubicacion" />
        <p>Ciudad de México</p>
      </div>
    </div>
  );
}

export default GeneroHome;
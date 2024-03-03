import React from 'react';
import './GeneroHome2.css';
import Ubicacion from '../../../../assets/Ubicacion.png';

const GeneroHome2 = () => {
  return (
    <div className="genero-home-container">
      <div>
        <p><strong>Genero:</strong> Masculino</p>
        <p><strong>Pronombre:</strong> El</p>
      </div>
      <div className="ubicacion-container">
        <img src={Ubicacion} alt="Ubicacion" />
        <p>Antofagasta</p>
      </div>
    </div>
  );
}

export default GeneroHome2;
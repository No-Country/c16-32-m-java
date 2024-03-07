import React from 'react';
import './GeneroHome.css';
import Ubicacion from '../../../assets/Ubicacion.png';

const GeneroHome = ({gender, pronouns}) => {
  return (
    <div className="genero-home">
      <div className='gender mx-4'>
        <p><strong>Genero:</strong>{gender}</p>
        <p><strong>Pronombre:</strong>{pronouns}</p>
      </div>
      <div className="ubicacion-container">
        <img src={Ubicacion} alt="Ubicacion" />
        <p>Ciudad de México</p>
      </div>
    </div>
  );
}

export default GeneroHome;
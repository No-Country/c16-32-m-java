import React from 'react';
import './Preferencias.css';
import Bici from '../../../assets/Bici.png';
import Peliculas from '../../../assets/Peliculas.png'; 
import Estrellas from '../../../assets/Estrellas.png';

const Preferencias = () => {
  return (
    <div className="preferencias-container">
      <button className="preferencia-btn">
        <img src={Bici} alt="Caminar" className="icono" />
        <span>Actividades: Caminar, Ciclismo</span>
      </button>
      <button className="preferencia-btn">
        <img src={Peliculas} alt="Películas/Series" className="icono" />
        <span>Películas o series: El juego del Calamar</span>
      </button>
      <button className="preferencia-btn">
        <img src={Estrellas} alt="Introvertido" className="icono" />
        <span>Me considero: Introvertido</span>
      </button>
    </div>
  );
}

export default Preferencias;
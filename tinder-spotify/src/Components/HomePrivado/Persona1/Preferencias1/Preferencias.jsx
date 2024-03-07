import React from 'react';
import './Preferencias.css';
import Bici from '../../../../assets/Bici.png';
import Peliculas from '../../../../assets/movies.png'; 
import estrella from '../../../../assets/estrella.png';

const Preferencias = () => {
  return (
    <div className="preferencias-container">
      <button className="preferencia-btn">
        <img src={Bici} alt="Caminar" className="icon1" />
        <span className='act1'>Actividades: Caminar, Ciclismo</span>
      </button>
      <button className="preferencia-btn">
        <img src={Peliculas} alt="Películas/Series" className="icon2" />
        <span className="act2">Películas o series: El juego del Calamar</span>
      </button>
      <button className="preferencia-btn">
        <img src={estrella} alt="Introvertido" className="icon3" />
        <span className="act3">Me considero: Introvertido</span>
      </button>
    </div>
  );
}

export default Preferencias;
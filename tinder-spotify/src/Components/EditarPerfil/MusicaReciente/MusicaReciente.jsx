import React, { useState } from 'react';
import CancionesEscuchadasLoader from '../../../hook/CancionesEscuchadasLoader';

import './MusicaReciente.css';
import Spotify from '../../../assets/Spotify.png';

const MusicaReciente = () => {
  const [cancionesEscuchadas, setCancionesEscuchadas] = useState([]);

  const handleCancionesCargadas = (canciones) => {
    setCancionesEscuchadas(canciones);
  };

  const agregarCancionEscuchada = (cancion) => {
    setCancionesEscuchadas([...cancionesEscuchadas, cancion]);
  };

  return (
    <>
      <div className="cabecera-musica-reciente">
        <h4>Últimas canciones escuchadas:</h4>
      </div>
      <div className="contenedor-con-borde">
        <div className="musica-reciente">
          {cancionesEscuchadas &&
            cancionesEscuchadas.map((cancion, index) => (
              <div key={index} className="cancion">
                <button className="boton-cancion"
                  onClick={() => agregarCancionEscuchada(cancion)}>
                  <img src={Spotify} alt={`Logo de la 
                canción ${index + 1}`} />
                  <span>{cancion}</span>
                </button>
              </div>
            ))}
        </div>
      </div>
      {/*<CancionesEscuchadasLoader onCancionesCargadas={handleCancionesCargadas} />*/}
    </>
  );
};

export default MusicaReciente;
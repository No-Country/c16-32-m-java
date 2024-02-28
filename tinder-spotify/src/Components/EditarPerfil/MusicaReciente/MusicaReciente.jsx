import React from 'react';
import './MusicaReciente.css'

import Spotify from '../../../assets/Spotify.png';

const MusicaReciente = () => {
  return (
    <>
        <div className="cabecera-musica-reciente">
        <h4>Últimas canciones escuchadas:</h4>
        </div>
        <div class="contenedor-con-borde">
          <div className="musica-reciente">
            <div className="cancion">
              <button className="boton-cancion">
                  <img src={Spotify} alt="Logo de la canción 1" />
                  <span>Billie Eilish - lovely</span>
              </button>
            </div>
            <div className="song">
              <button className="song-button">
                  <img src={Spotify} alt="Logo de la canción 2" />
                  <span>Nene malo - Bailan las rochas y chetas</span>
              </button>
            </div>
            <div className="song">
              <button className="song-button">
                  <img src={Spotify} alt="Logo de la canción 3" />
                  <span>Billie Eilish - lovely</span>
              </button>
            </div>
          </div>
        </div>    
    </>    
  );
};

export default MusicaReciente;
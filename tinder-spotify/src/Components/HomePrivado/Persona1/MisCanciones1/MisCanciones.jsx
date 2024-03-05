import React from 'react';
import './MisCanciones.css';
import Spotify from '../../../../assets/Spotify.png'; 

const MisCanciones = ({currentSong}) => {
  return (
    <div className="mis-canciones-container">
      <h2 className="tituca">Mis canciones favoritas</h2>
      <div className="cancion">
        <img src={Spotify} alt="Spotify" className="logo-spotify" />
        <span>The White Stripes - Seven Nation Army</span>
      </div>
      <div className="cancion">
        <img src={Spotify} alt="Spotify" className="logo-spotify" />
        <span>Amy Winehouse - Back To Black</span>
      </div>
      <div className="cancion">
        <img src={Spotify} alt="Spotify" className="logo-spotify" />
        <span>{currentSong}</span>
      </div>
    </div>
  );
}

export default MisCanciones;
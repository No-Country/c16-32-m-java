import React from 'react';
import './MisCanciones2.css';
import Spotify from '../../../../assets/Spotify.png'; 

const MisCanciones2 = () => {
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
        <span>Gorillaz - Feel Good Inc</span>
      </div>
    </div>
  );
}

export default MisCanciones2;
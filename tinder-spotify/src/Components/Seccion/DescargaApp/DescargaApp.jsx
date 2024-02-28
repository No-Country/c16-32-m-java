import React from 'react';
import './DescargaApp.css';

import AppStore from '../../../assets/AppStore.png'
import GooglePlay from '../../../assets/GooglePlay.png'

const DescargaApp = () => {
  return (
    <>
      <div className="descarga-app">
          <h1 className="descarga-title">¡Descarga la app!</h1>
          <div className="logos">
            <img src={AppStore} alt="App Store" />
            <img src={GooglePlay} alt="Google Play" />
          </div>
      </div>
    </>
  
  );
};

export default DescargaApp;
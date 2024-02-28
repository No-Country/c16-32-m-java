import React from 'react';
import './TituloHome.css';
import log from '../../../assets/log.png';

const TituloHome = () => {
  return (
    <div className="ti-pri-container">
      <div className="logo-conte2">
        <img src={log} alt="log" className="log1" />
        <h2 className="tituChat">ChatBeat</h2>
      </div>
      <div className="bot-container">
        <button className="bot-f">Filtrar Preferencias</button>
      </div>
    </div>
  );
}

export default TituloHome;
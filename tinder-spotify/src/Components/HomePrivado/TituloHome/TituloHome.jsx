import React from 'react';
import './TituloHome.css';
import '../../StyleVariables/Color.css'

const logo = getComputedStyle(document.documentElement).getPropertyValue('--img-logo');

const TituloHome = ({ onFilterClick }) => {
  return (
    <div className="ti-pri-container">
      <div className="logo-conte2">
        <img  className="img-logo" />
        <h2 className="tituChat">ChatBeat</h2>
      </div>
      <div className="bot-container">
        <button className="bot-f" onClick={onFilterClick}>Filtrar Preferencias</button>
      </div>
    </div>
  );
}

export default TituloHome;
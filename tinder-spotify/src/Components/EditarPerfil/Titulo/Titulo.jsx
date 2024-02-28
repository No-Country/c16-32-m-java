import React from 'react';
import log from '../../../assets/log.png';

import './Titulo.css';

const Titulo = () => {
  return (
    <div className="titulo-container">
      <div className="icono-cont">
        <img src={log} alt="log" />
        <h2 className="Sub-chat">ChatBeat</h2>
      </div>  
      <h1 className="titulo">Editar Perfil</h1>
    </div>
  );
}

export default Titulo;
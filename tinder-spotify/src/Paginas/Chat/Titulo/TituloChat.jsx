import React from 'react';
import { Link } from 'react-router-dom';

import './TituloChat.css';

const TituloChat= () => {
  return (
    <div className="titulo-container">
      <Link to="/home-privado" className="icono-cont">
        <img className="img-logo" />
        <h2 className="Sub-chat">ChatBeat</h2>
      </Link>
      <h1 className="titulo">Chat</h1>
    </div>
  );
}

export default TituloChat;
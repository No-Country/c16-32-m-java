import React from 'react';
import './TituloUserD.css';
import { Link } from 'react-router-dom';



const TituloUserD = () => {
  return (
    <div className="ti-pri-container">
      <div className="logo-conte2">
        <img  className="img-logo" />
        <h2 className="tituChat">ChatBeat</h2>
      </div>
      <div className="bot-container">
      <Link to="/editar-perfil" className="icono-cont">
        <button className="bot-f">
        Salir de vista previa
        </button>
        
    </Link>
      </div>
    </div>
  );
}

export default TituloUserD;
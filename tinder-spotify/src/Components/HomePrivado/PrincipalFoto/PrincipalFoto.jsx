import React from 'react';
import './PrincipalFoto.css';
import fleIzqui from '../../../assets/fleIzqui.png'
import fledere from '../../../assets/fledere.png';
import flecha from '../../../assets/flecha.png';

const PrincipalFoto = () => {
  return (
    <div className="Principal-container">
      <div className="barra-carga-container">
        <div className="barra-carga">
          <div className="carga-completa"></div>
        </div>
        <div className="redondela" ></div>
      </div>
      <div className="botoness">
        <img className="flecha" src={flecha} alt="Flecha dere" />
      </div>
      <div className="bot9">
        <button className="atras">
          <img src={fleIzqui} alt="Atrás" /> Atrás
        </button>
        <button className="chat">Chat</button>
        <button className="siguiente">
          Siguiente <img src={fledere} alt="Siguiente" />
        </button>
      </div>
    </div>
  );
}

export default PrincipalFoto;
import React from 'react';
import './PrincipalFoto.css';

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
        <img className="flecha-dere" src="" alt="Flecha dere" />
      </div>
      <div>
        <button className="atras">Atrás</button>
        <button className="chat">Chat</button>
        <button className="siguiente">Siguiente</button>
      </div>
    </div>
  );
}

export default PrincipalFoto;
import React, { useState } from 'react';
import './PrincipalFoto2.css';

import fleIzqui from '../../../../assets/fleIzqui.png';
import fledere from '../../../../assets/fledere.png';
import flecha from '../../../../assets/flecha.png';
import flechaiz from '../../../../assets/flechaiz.png'
import fondo5 from '../../../../assets/fondo5.png';
import fondo6 from '../../../../assets/fondo6.png';
import fondo7 from '../../../../assets/fondo7.png';
import fondo8 from '../../../../assets/fondo8.png';

const fotos = [fondo5, fondo6, fondo7, fondo8];

const PrincipalFoto2 = ({ onNextClick, onPrevClick }) => {
  const [fotoActual, setFotoActual] = useState(0);
  const [mostrarFlechaIzquierda, setMostrarFlechaIzquierda] = useState(false);
  
  const avanzarFoto = () => {
    setFotoActual((prevFoto) => {
      if (prevFoto + 1 < fotos.length) {
        setMostrarFlechaIzquierda(true); // Mostrar la flecha izquierda si no estamos en la última foto
      } else {
        setMostrarFlechaIzquierda(false); // Ocultar la flecha izquierda si estamos en la última foto
      }
      return (prevFoto + 1) % fotos.length;
    });
  };

  const retrocederFoto = () => {
    setFotoActual((prevFoto) => {
      if (prevFoto - 1 >= 0) {
        setMostrarFlechaIzquierda(true); // Mostrar la flecha izquierda si no estamos en la primera foto
      } else {
        setMostrarFlechaIzquierda(false); // Ocultar la flecha izquierda si estamos en la primera foto
      }
      return (prevFoto - 1 + fotos.length) % fotos.length;
    });
  };

  const handlePrevClick = () => {
    retrocederFoto();
  };

  return (
    <div className="Principal-container" style={{ backgroundImage: `url(${fotos[fotoActual]})` }}>
      <div className="barra-carga-container">
        <div className="barra-carga">
          <div className="carga-completa"></div>
        </div>
        <div className="redondela"></div>
      </div>
      <div className="botoness">
        {mostrarFlechaIzquierda && <img className="flechaiz" src={flechaiz} alt="Flecha iz" onClick={handlePrevClick} />}
        <img className="flecha" src={flecha} alt="Flecha dere" onClick={avanzarFoto} />
      </div>
      <div className="bot9">
        <button className="atras"  onClick={onPrevClick}>
          <img src={fleIzqui} alt="Atrás" /> Atrás
        </button>
        <button className="chat">Chat</button>
        <button className="siguiente" onClick={onNextClick}>
          Siguiente <img src={fledere} alt="Siguiente" />
        </button>
      </div>
    </div>
  );
};

export default PrincipalFoto2;
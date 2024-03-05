import React, { useState } from 'react';
import './PrincipalFoto.css';

import fleIzqui from '../../../assets/fleIzqui.png';
import fledere from '../../../assets/fledere.png';
import flecha from '../../../assets/flecha.png';
import flechaiz from '../../../assets/flechaiz.png'
import fondo1 from '../../../assets/fondo1.png';
import fondo2 from '../../../assets/fondo2.png';
import fondo3 from '../../../assets/fondo3.png';
import fondo4 from '../../../assets/fondo4.png';

const fotos = [fondo1, fondo2, fondo3, fondo4];

const PrincipalFoto = ({ onNextClick }) => {
  const [fotoActual, setFotoActual] = useState(0);
  const [mostrarFlechaIzquierda, setMostrarFlechaIzquierda] = useState(false);

  const avanzarFoto = () => {
    setFotoActual((prevFoto) => {
      if (prevFoto + 1 < fotos.length) {
        setMostrarFlechaIzquierda(true); 
      } else {
        setMostrarFlechaIzquierda(false); 
      }
      return (prevFoto + 1) % fotos.length;
    });
  };

  const retrocederFoto = () => {
    setFotoActual((prevFoto) => {
      if (prevFoto - 1 >= 0) {
        setMostrarFlechaIzquierda(true); 
      } else {
        setMostrarFlechaIzquierda(false); 
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
    </div>
  );
};

export default PrincipalFoto;

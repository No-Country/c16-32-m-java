import React from 'react'
import './BotonChico.css';

const BotonChico = ({ texto, color, textoInterior }) => {
    const colorClass = color === '#B53550' ? 'rojo' : '';
  
    return (
      <button className={`Boton-chico ${colorClass}`}>
        {textoInterior || texto}
      </button>
    );
  }
  
  export default BotonChico;




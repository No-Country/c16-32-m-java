import React from 'react'
import './BotonChico.css';

const BotonChico = ({ texto, color, textoInterior }) => {
  const colorClass = color === 'var(--primary-color)' ? 'rojo' : '';

  return (
    <button className={`Boton-chico ${colorClass}`}>
      {textoInterior || texto}
    </button>
  );
}

export default BotonChico;




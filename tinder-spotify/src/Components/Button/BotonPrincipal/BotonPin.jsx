import React from 'react';
import './BotonPin.css';

const BotonPin = ({ texto }) => {
  return (
    <button className="boton-pin">{texto}</button>
  );
}

export default BotonPin;
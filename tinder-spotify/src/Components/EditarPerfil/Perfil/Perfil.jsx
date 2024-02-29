import React from 'react';
import './Perfil.css';

import foto from '../../../assets/foto.png';

const Perfil = ({name, onNameChange,
  birthdate, onBirthdateChange}) => {

    //De momento hardcodear fotos acá, porque no se pueden
    //guardar en base de datos sin bardearla.

  return (
    <div className="perfil-seccion">
      <div className="perfil-foto">
        <img src={foto} alt="Perfil" />
      </div>
      <div className="perfil-info">
        <input classname="name" type="text" onChange={onNameChange} value={name}/>
        <input className="edad" type="date" onChange={onBirthdateChange} value={birthdate}/>
      </div>
    </div>
  );
};

export default Perfil;
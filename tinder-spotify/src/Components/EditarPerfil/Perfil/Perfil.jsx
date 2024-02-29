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
        <input className="name" type="text" onChange={(event)=>{onNameChange(event.target.value)}} value={name}/>
        <input className="edad" type="date" onChange={(event)=>{onBirthdateChange(event.target.value)}} value={birthdate}/>
      </div>
    </div>
  );
};

export default Perfil;
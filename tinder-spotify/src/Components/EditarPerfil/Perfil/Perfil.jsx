import React from 'react';
import './Perfil.css';

import foto2 from '../../../assets/foto2.png';

const Perfil = ({name, onNameChange,
  birthdate, onBirthdateChange}) => {

    //De momento hardcodear fotos acá, porque no se pueden
    //guardar en base de datos sin bardearla.

  return (
    <div className="perfil-seccion">
      <div className="perfil-foto">
        <img src={foto2} alt="Perfil" />
      </div>
      <div className="perfil-info mt-2">
        <div className='my-3'>
        <label htmlFor="name" className='form-label'>Editar nombre </label>
        <input id='name' className="name form-control" type="text" onChange={(event)=>{onNameChange(event.target.value)}} value={name}/>
        </div>
        <label htmlFor="name"  className='form-label'>Editar Fecha de Nacimiento </label>
        <input id="edad"className="edad form-control" type="date" onChange={(event)=>{onBirthdateChange(event.target.value)}} value={birthdate}/>
      </div>
    </div>
  );
};

export default Perfil;
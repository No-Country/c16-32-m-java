import React from 'react';
import './PerfiHome.css';
import PorceImage from '../../../assets/PorceImage.png';

const PerfiHome = ({ name, birthdate }) => {

  const calculateAge = (birthdate) => {
    const today = new Date();
    const birthDate = new Date(birthdate);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();

    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }

    return age;
  }


  return (
    <div className="perfil-seccion-custom">
      <div className="perfil-info-custom">
        <p>{name}</p>
        <p className="edad-custom">{calculateAge(birthdate)}</p>
      </div>
    </div>
  );
};

export default PerfiHome;

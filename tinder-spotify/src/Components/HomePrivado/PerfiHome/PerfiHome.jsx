import React from 'react';
import './PerfiHome.css';
import PorceImage from '../../../assets/PorceImage.png';

const PerfiHome = ({name, birthdate}) => {
  console.log(name, birthdate)
  return (
    <div className="perfil-seccion-custom">
      <div className="perfil-info-custom">
        <p className="edad-custom">{birthdate}</p>
        <p>{name}</p>
        {/*<div className="porce-con">
          <img src={PorceImage} alt="Porce" className="Porce" />
        </div>*/}

      </div>
    </div>
  );
};

export default PerfilHome;

import React from 'react';
import './PerfilHome2.css';
import PorceImage from '../../../../assets/PorceImage.png';

const PerfilHome2 = () => {
  return (
    <div className="perfil-seccion-custom">
      <div className="perfil-info-custom">
        <p className="edad-custom">27,</p>
        <p>Julian</p>
        <div className="porce-con">
          <img src={PorceImage} alt="Porce" className="Porce" />
        </div>
      </div>
    </div>
  );
};

export default PerfilHome2;
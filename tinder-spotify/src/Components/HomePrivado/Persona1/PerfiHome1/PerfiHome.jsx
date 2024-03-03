import React from 'react';
import './PerfiHome.css';
import PorceImage from '../../../../assets/PorceImage.png';

const PerfiHome = () => {
  return (
    <div className="perfil-seccion-custom">
      <div className="perfil-info-custom">
        <p className="edad-custom">28,</p>
        <p>Daniel</p>
        <div className="porce-con">
          <img src={PorceImage} alt="Porce" className="Porce" />
        </div>
      </div>
    </div>
  );
};

export default PerfiHome;
import React from 'react';
import './PerfilHome.css';

const PerfiHome = ({name, birthdate}) => {
  return (
    <div className="perfil-seccion-custom">
      <div className="perfil-info-custom">
        <p className="edad-custom">{{birthdate}}</p>
        <p>{{name}}</p>
      </div>
    </div>
  );
};

export default PerfiHome;
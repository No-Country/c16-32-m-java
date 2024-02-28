import React from 'react';
import './Genero.css';

const Genero = () => {
  return (
    <div className="seccion-desplazable">
      <h2>¿Cuál es tu género?</h2>
      <div className="botones-radio">
      <label className="radio-label">
        <input type="radio" name="gender" value="female" />
            Femenino
        </label>
        <label className="radio-label">
         <input type="radio" name="gender" value="male" />
            Masculino
        </label>
        <label className="radio-label">
        <input type="radio" name="gender" value="other" />
            Otros
        </label>
      </div>
      <div className="input-label-custom">
        <label htmlFor="pronombres" className="input-label">
          Pronombres:
        </label>
        <input id="pronombres" type="text" placeholder="El/ella" className="input-custom" />
      </div>
    </div>
  );
};

export default Genero;
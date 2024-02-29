import React from 'react';
import './Genero.css';

const Genero = ({ gender, onGenderChange,
  pronouns, onPronounsChange }) => {


  return (
    <div className="seccion-desplazable">
      <h2>¿Cuál es tu género?</h2>
      <div className="botones-radio">
      <label className="radio-label">
        <input type="radio" name="gender" value="FEMENINO" 
        checked={gender === "FEMENINO"} 
        onChange={(event)=>{onGenderChange(event.target.value)}}/>
            Femenino
        </label>
        <label className="radio-label">
         <input type="radio" name="gender" value="MASCULINO" 
         checked={gender === "MASCULINO"} 
         onChange={(event)=>{onGenderChange(event.target.value)}}/>
            Masculino
        </label>
        <label className="radio-label">
        <input type="radio" name="gender" value="OTRX" 
        checked={gender === "OTRX"}
        onChange={(event)=>{onGenderChange(event.target.value)}}/>
            Otrx
        </label>
      </div>
      <div className="input-label-custom">
        <label htmlFor="pronombres" className="input-label">
          Pronombres:
        </label>
        <input id="pronombres" type="text" 
        placeholder="el/ella/elle" className="input-custom"
        value={pronouns} onChange={(event)=>{onPronounsChange(event.target.value)}}/>
      </div>
    </div>
  );
};

export default Genero;
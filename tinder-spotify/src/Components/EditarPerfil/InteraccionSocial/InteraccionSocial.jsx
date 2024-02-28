import React from 'react';
import './InteraccionSocial.css';

const InteraccionSocial = () => {
  return (
    <div className="interaccion-social">
       <p>¿Cuál es tu nivel de interacción social?</p>
       <div className="botones-radio">
            <label className="radio-label-bajo">
            <input type="radio" name="socialInteraction" value="high" />
              Alto
            </label>
            <label className="radio-label-bajo">
              <input className="borde-rojo" type="radio" name="socialInteraction" value="medium" />
              Medio
            </label>
            <label className="radio-label-bajo">
              <input type="radio" name="socialInteraction" value="low" />
              Bajo
            </label>
       </div>
    </div>
  );
};

export default InteraccionSocial;
import React from 'react';
import './InteraccionSocial.css';

const InteraccionSocial = ({socialBattery, 
  onSocialBatteryChange}) => {


  return (
    <div className="interaccion-social">
       <p>¿Cuál es tu nivel de interacción social?</p>
       <div className="botones-radio">
            <label className="radio-label-bajo">
            <input type="radio" name="socialInteraction" value="AVAILABLE"
            checked={socialBattery === "AVAILABLE"} 
            onChange={() => {onSocialBatteryChange("AVAILABLE")}} />
              Disponible
            </label>
            <label className="radio-label-bajo">
              <input className="borde-rojo" type="radio" 
              name="socialInteraction" value="BUSY"
              checked={socialBattery === "BUSY"} 
              onChange={() => {onSocialBatteryChange("BUSY")}} />
              Ocupadx
            </label>
            <label className="radio-label-bajo">
              <input type="radio" name="socialInteraction" 
              value="AWAY" checked={socialBattery === "AWAY"} 
              onChange={() => {onSocialBatteryChange("AWAY")}} />
              No disponible
              </label>
       </div>
    </div>
  );
};

export default InteraccionSocial;
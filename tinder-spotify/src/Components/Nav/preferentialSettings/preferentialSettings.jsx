import React, { useState } from 'react';
import './preferentialSettings.css';


const PreferentialSettings = ({ onClose }) => {
  const [opcionSeleccionada, setOpcionSeleccionada] = useState('amistad');
  const [edadDesde, setEdadDesde] = useState(18);
  const [edadHasta, setEdadHasta] = useState(120);
  const [estadoRelacion, setEstadoRelacion] = useState('disponible');

  const handleSeleccion = (event) => {
    setOpcionSeleccionada(event.target.value);
  };

  const handleEdadDesde = (event) => {
    setEdadDesde(parseInt(event.target.value, 10));
  };

  const handleEdadHasta = (event) => {
    setEdadHasta(parseInt(event.target.value, 10));
  };

  const handleEstadoRelacion = (event) => {
    setEstadoRelacion(event.target.value);
  };

  return (
    <div className="modal-overlay">
      <div className="modal-preferences">
      <button onClick={onClose} className="close-button-ch">x</button>
        <div className="modal-header-ch">
          <span className='letter '>Ajustes de preferencia</span>
        
        </div>
        <div className="modal-content-ch">
          <div className="filter-options">
          <span className='letter2'>¿Qué estoy buscando?</span>
            <div className="opcion">
              <input
                type="radio"
                id="amistad"
                value="amistad"
                checked={opcionSeleccionada === 'amistad'}
                onChange={handleSeleccion}
              />
              <label htmlFor="amistad">Amistad</label>
            </div>

            <div className="opcion">
              <input
                type="radio"
                id="casual"
                value="casual"
                checked={opcionSeleccionada === 'casual'}
                onChange={handleSeleccion}
              />
              <label htmlFor="casual">Casual</label>
            </div>

            <div className="opcion">
              <input
                type="radio"
                id="relacion"
                value="relacion"
                checked={opcionSeleccionada === 'relacion'}
                onChange={handleSeleccion}
              />
              <label htmlFor="relacion">Relación</label>
              </div>
              
          </div>
          <div className="age-range">
            <p className='letter2'>Rango de edad</p>
            <div className="age-input">
              <label>Desde: </label>
              <input
                type="number"
                value={edadDesde}
                min="18"
                max="120"
                onChange={handleEdadDesde}
              />
            </div>
            <div className="age-input">
              <label>Hasta: </label>
              <input
                type="number"
                value={edadHasta}
                min="18"
                max="120"
                onChange={handleEdadHasta}
              />
            </div>
          </div>
       
          <div className='availability-container'>
          <p>Disponibilidad</p>
          <select
            id="estadoRelacion"
            value={estadoRelacion}
            onChange={handleEstadoRelacion}
          >
            <option value="disponible">Disponible</option>
            <option value="ocupado">Ocupado</option>
            <option value="noDisponible">No Disponible</option>
          </select>
        </div>
         <div>
            <div className='div-pequeno'>
            <p>
              *Nivel de conectividad en la app:
              <br />
              Disponible: Atenta a la aplicación.
              <br />
              Ocupado: Esta realizando otras actividades.
              <br />
              No Disponible: Actualmente fuera de la aplicación.
            </p>
          </div>
            {/* <div className='availability-container2'>
              <p className='letter2'>Idioma</p>
              <button className='large-button'>Español</button><br />
              <button className='large-button'>Inglés</button><br />
              <button className='large-button'>Francés</button>
            </div> */}
          </div>
        </div>
        <div className="modal-footer">
          <button onClick={onClose} className="save-button">Guardar cambios</button>
        </div>
      </div>
    </div>
  );
}

export default PreferentialSettings;

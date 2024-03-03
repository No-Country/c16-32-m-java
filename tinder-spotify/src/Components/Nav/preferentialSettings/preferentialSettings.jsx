import React from 'react';
import './preferentialSettings.css';

const PreferentialSettings = ({ onClose }) => {
  return (
    <div className="modal-overlay">
      <div className="modal">
        <div className="modal-header">
          <h2>Ajustes de preferencia</h2>
          <button onClick={onClose} className="close-button">x</button>
        </div>
        <div className="modal-content">
          <div className="searching-for">
            <p>¿Qué estoy buscando?</p>
          </div>
          <div className="filter-options">
            <p>Chat</p>
            <p>Masculino</p>
            <p>Otrxs</p>
          </div>
          <div className="age-range">
            <p>Rango de edad</p>
            <p>18 - 60</p>
          </div>
          <div className="physical-activity">
            <p>Actividad física</p>
          </div>
        </div>
        <div className="modal-footer">
          <button className="save-button">Guardar cambios</button>
        </div>
      </div>
    </div>
  );
}

export default PreferentialSettings;
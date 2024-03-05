import React, { useState } from 'react';
import './preferentialSettings.css';

const PreferentialSettings = ({ onClose }) => {
  const [opcionesSeleccionadas, setOpcionesSeleccionadas] = useState(['amistad']);
  const [edadDesde, setEdadDesde] = useState(18);
  const [edadHasta, setEdadHasta] = useState(120);
  const [estadoRelacion, setEstadoRelacion] = useState('disponible');
  const [generoInteres, setGeneroInteres] = useState([]);
  const [porcentaje, setPorcentaje] = useState(0);

  const handleSeleccion = (event) => {
    const opcion = event.target.value;
    const opcionesActuales = [...opcionesSeleccionadas];

    if (opcionesActuales.includes(opcion)) {
      const index = opcionesActuales.indexOf(opcion);
      opcionesActuales.splice(index, 1);
    } else {
      opcionesActuales.push(opcion);
    }

    setOpcionesSeleccionadas(opcionesActuales);
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

  const handleGeneroInteres = (event) => {
    const genero = event.target.value;
    const generosActuales = [...generoInteres];

    if (generosActuales.includes(genero)) {
      const index = generosActuales.indexOf(genero);
      generosActuales.splice(index, 1);
    } else {
      generosActuales.push(genero);
    }

    setGeneroInteres(generosActuales);
  };

  const handlePorcentajeChange = (event) => {
    setPorcentaje(parseInt(event.target.value, 10));
  };

  const calcularPorcentajeTotal = () => {
    const porcentajes = {
      'Melodías gemelas': 100,
      'Ritmo perfecto': 75,
      'Buenos acordes': 50,
      'Notas similares': 25,
      'No desafinan': 0,
    };

    let porcentajeTotal = 0;

    opcionesSeleccionadas.forEach((opcion) => {
      porcentajeTotal += porcentajes[opcion];
    });

    return porcentajeTotal;
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <div className="modal-header">
          <span className='letter'>Ajustes de preferencia</span>
          <button onClick={onClose} className="close-button">x</button>
        </div>
        <div className="modal-content">
          <div className="filter-options">
            <span className='letter2'>¿Qué estoy buscando?</span>
            <div className="letter17">
              <input
                type="checkbox"
                id="amistad"
                value="amistad"
                checked={opcionesSeleccionadas.includes('amistad')}
                onChange={handleSeleccion}
              />
              <label className='letter20'>Amistad</label>
            </div>

            <div className="letter17">
              <input
                type="checkbox"
                id="casual"
                value="casual"
                checked={opcionesSeleccionadas.includes('casual')}
                onChange={handleSeleccion}
              />
              <label className='letter20'>Casual</label>
            </div>

            <div className="opcion">
              <input
                type="checkbox"
                id="relacion"
                value="relacion"
                checked={opcionesSeleccionadas.includes('relacion')}
                onChange={handleSeleccion}
              />
              <label className='letter20'>Relación</label>
            </div>
          </div>
          <div className="age-range">
            <p className='letter2'>Rango de edad</p>
            <div className="age-input">
              <label className='letter20'>Desde: </label>
              <input
                className='letter18'
                type="number"
                value={edadDesde}
                min="18"
                max="120"
                onChange={handleEdadDesde}
              />
            </div>
            <div className="age-input">
              <label className='letter20'>Hasta: </label>
              <input
                className='letter18'
                type="number"
                value={edadHasta}
                min="18"
                max="120"
                onChange={handleEdadHasta}
              />
            </div>
          </div>

          <div className='availability-container'>
            <p className='letter2'>Disponibilidad</p>
            <select
              id="estadoRelacion"
              value={estadoRelacion}
              onChange={handleEstadoRelacion}
              className='letter18'
            >
              <option value="disponible">Disponible</option>
              <option value="ocupado">Ocupado</option>
              <option value="noDisponible">No Disponible</option>
            </select>
          </div>

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

          <div className="filter-options">
            <span className='letter2'>¿Qué género me interesa?</span>
            <div className="opcion">
              <input
                type="checkbox"
                id="mujer"
                value="mujer"
                checked={generoInteres.includes('mujer')}
                onChange={handleGeneroInteres}
              />
              <label htmlFor="mujer" className='letter20'>Mujer</label>
            </div>

            <div className="opcion">
              <input
                type="checkbox"
                id="hombre"
                value="hombre"
                checked={generoInteres.includes('hombre')}
                onChange={handleGeneroInteres}
              />
              <label htmlFor="hombre" className='letter20'>Hombre</label>
            </div>

            <div className="opcion">
              <input
                type="checkbox"
                id="noBinario"
                value="noBinario"
                checked={generoInteres.includes('noBinario')}
                onChange={handleGeneroInteres}
              />
              <label htmlFor="noBinario" className='letter20'>No binario</label>
            </div>
          </div>

          <div className="modal-footer">
          <label className="age-range">
          <strong className='letter2'>Elige el porcentaje de compatibilidad que deseas:</strong>
          
          <br />
          Melodías gemelas: 75% a 100%
          <br />
          Ritmo perfecto: 50% a 75%
          <br />
          Buenos acordes: 25% a 50%
          <br />
          No desafinan: 0% a 25%
          <br />

            <input
              className='letter18'
              type="number"
              id="porcentaje"
              name="porcentaje"
              min="0"
              max="100"
              value={porcentaje}
              onChange={handlePorcentajeChange}
            /></label>
            <button className="save-button">Guardar cambios</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PreferentialSettings;

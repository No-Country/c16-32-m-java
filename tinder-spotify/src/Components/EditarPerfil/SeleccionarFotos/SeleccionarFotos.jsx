import React from 'react'
import './SeleccionarFotos.css';

import xdx from '../../../assets/xdx.png';
import cerrar from '../../../assets/cerrar.png';
import mas from '../../../assets/mas.png';

const SeleccionarFotos = () => {
  return (
    <>
      <div className="seleccionar-fotos-container">
        <h2 className="titulo-foto">Seleccionar Fotos <img src={xdx} alt="xdx" className="xdx" /></h2>
        <div className="cuadritos">
          {[1, 2, 3, 4, 5, 6].map(numero => (
            <div key={numero} className="cuadrito">
              <img src={numero <= 3 ? cerrar : mas} alt={`Icono ${numero}`} className="icono-cuadrito" />
            </div>
          ))}
        </div>
        <div className="botones-seleccion">
          <button className="boton-cancelar">Cancelar</button>
          <button className="boton-guardar">Guardar Cambios</button>
        </div>
      </div>
    </>
  )
}

export default SeleccionarFotos;
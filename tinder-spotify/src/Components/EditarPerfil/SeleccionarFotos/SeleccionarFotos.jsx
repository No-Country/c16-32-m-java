import React from 'react';
import './SeleccionarFotos.css';

import xdx from '../../../assets/xdx.png';
import cerrar from '../../../assets/cerrar.png';
import mas from '../../../assets/mas.png';
import selec1 from '../../../assets/selec1.png';
import selec2 from '../../../assets/selec2.png';
import selec3 from '../../../assets/selec3.png';
import selec4 from '../../../assets/selec4.png';

const SeleccionarFotos = ({ cambiarTarjeta }) => {
  const cuadritos = [
    { imagen: selec1, icono: cerrar }, 
    { imagen: selec2, icono: cerrar }, 
    { imagen: selec3, icono: cerrar }, 
    { imagen: selec4, icono: cerrar },  
    { contenido: 'Da click para agregar fotos desde tu dispositivo', icono: mas } 
  ];

  return (
    <div className="seleccionar-fotos-container">
      <h2 className="titulo-foto">Seleccionar Fotos <img src={xdx} alt="xdx" className="xdx" onClick={cambiarTarjeta} /></h2>
      <div className="cuadritos">
        {cuadritos.map((item, index) => (
          <div key={index} className="cuadrito">
            {item.imagen && <img src={item.imagen} alt={`Imagen ${index}`} className="icono-cuadrito" />}
            {item.contenido && <p>{item.contenido}</p>}
            {item.icono && <img src={item.icono} alt={`Icono ${index}`} className="icono-esquina" />}
          </div>
        ))}
      </div>
      <div className="botones-seleccion">
        <button className="boton-cancelar" onClick={cambiarTarjeta}>Cancelar</button>
        <button className="boton-guardar">Guardar Cambios</button>
      </div>
    </div>
  );
};

export default SeleccionarFotos;
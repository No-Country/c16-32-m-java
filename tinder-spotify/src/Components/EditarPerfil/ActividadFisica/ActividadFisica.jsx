import React from 'react';
import './ActividadFisica.css';

import BotonChico from '../../Button/BotonChico/BotonChico';

const ActividadFisica = () => {
  return (
    <div className="contenedor-con-borde-bo">
      <div className="seccion-actividad">
        <div className="titulo-actividad">
          <p>¿Qué actividad física realizas?</p>
          <button className="boton-menos">-</button>
        </div>
        <div className="botones-actividad">
          <BotonChico texto="Botón rojo" color="var(--primary-color)" textoInterior="Correr" />
          <BotonChico texto="Botón gris" color="#CDC5CB" textoInterior="Natación" />
          <BotonChico texto="Botón rojo" color="var(--primary-color)" textoInterior="Escalar" />
          <BotonChico texto="Botón gris" color="#CDC5CB" textoInterior="Ir Gym" />
          <BotonChico texto="Botón gris" color="#CDC5CB" textoInterior="Ciclismo" />
          <BotonChico texto="Botón gris" color="#CDC5CB" textoInterior="Caminar" />
          <BotonChico texto="Botón gris" color="#CDC5CB" textoInterior="yoga" />
        </div>
      </div>
    </div>
  );
};

export default ActividadFisica;
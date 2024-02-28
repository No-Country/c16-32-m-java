import React from 'react';
import './ActividadFisica.css';

import BotonChico from '../../Button/BotonChico/BotonChico';

const ActividadFisica = () => {
  return (
    <div class="contenedor-con-borde-bo">
      <div class="seccion-actividad">
        <div class="titulo-actividad">
          <p>¿Qué actividad física realizas?</p>
          <button class="boton-menos">-</button>
        </div>
        <div class="botones-actividad">
          <BotonChico texto="Botón rojo" color="#B53550" textoInterior="Correr" />
          <BotonChico texto="Botón gris" color="#CDC5CB" textoInterior="Natación" />
          <BotonChico texto="Botón rojo" color="#B53550" textoInterior="Escalar" />
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
import React, { useState, useEffect } from 'react';
import FotosPerfil from '../ElegirFotoPerfil/FotosPerfil';
import SeleccionarFotos from '../SeleccionarFotos/SeleccionarFotos';

const ControladorFotos = () => {
  const [porcentajeCarga, setPorcentajeCarga] = useState(0);
  const [mostrarSeleccionarFotos, setMostrarSeleccionarFotos] = useState(false);

  useEffect(() => {
    const cargaTemporizador = setTimeout(() => {
      setPorcentajeCarga(50);
    }, 2000);

    const mostrarTemporizador = setTimeout(() => {
      setMostrarSeleccionarFotos(true);
    }, 4000);

    const aumentoTemporizador = setInterval(() => {
      setPorcentajeCarga(prevPorcentaje => {
        const nuevoPorcentaje = prevPorcentaje + 1;
        return nuevoPorcentaje > 100 ? 100 : nuevoPorcentaje;
      });
    }, 50);

    return () => {
      clearTimeout(cargaTemporizador);
      clearTimeout(mostrarTemporizador);
      clearInterval(aumentoTemporizador);
    };
  }, []);

  return mostrarSeleccionarFotos ? <SeleccionarFotos /> : <FotosPerfil porcentajeCarga={porcentajeCarga} />;
};

export default ControladorFotos;
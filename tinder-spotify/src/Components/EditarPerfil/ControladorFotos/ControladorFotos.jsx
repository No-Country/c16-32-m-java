
import React, { useState } from 'react';
import FotosPerfil from '../ElegirFotoPerfil/FotosPerfil';
import SeleccionarFotos from '../SeleccionarFotos/SeleccionarFotos';

const ControladorFotos = () => {
  const [porcentajeCarga, setPorcentajeCarga] = useState(0);
  const [mostrarSeleccionarFotos, setMostrarSeleccionarFotos] = useState(false);
  const [cargaIniciada, setCargaIniciada] = useState(false);

  const iniciarCarga = () => {
    setCargaIniciada(true);
    const aumentoTemporizador = setInterval(() => {
      setPorcentajeCarga(prevPorcentaje => {
        const nuevoPorcentaje = prevPorcentaje + 1;
        return nuevoPorcentaje > 100 ? 100 : nuevoPorcentaje;
      });
    }, 50);

    setTimeout(() => {
      clearInterval(aumentoTemporizador);
      setMostrarSeleccionarFotos(true);
    }, 4000);
  };

  const cambiarTarjeta = () => {
    setPorcentajeCarga(0); 
    setCargaIniciada(false); 
    setMostrarSeleccionarFotos(!mostrarSeleccionarFotos);
  };

  return mostrarSeleccionarFotos ? (
    <SeleccionarFotos cambiarTarjeta={cambiarTarjeta} />
  ) : (
    <FotosPerfil
      porcentajeCarga={cargaIniciada ? porcentajeCarga : 0}
      iniciarCarga={iniciarCarga}
      cambiarTarjeta={cambiarTarjeta}
    />
  );
};

export default ControladorFotos;
import React, { useState, useEffect } from 'react';
import './EditarPerfil.css';
import Titulo from '../EditarPerfil/Titulo/Titulo';
import SideNav from '../Nav/SideNav/SideNav';
import Perfil from '../EditarPerfil/Perfil/Perfil';
import Genero from '../EditarPerfil/Genero/Genero';
import DescripcionTextarea from '../EditarPerfil/DescripcionTextarea/DescripcionTextarea';
import MusicaReciente from '../EditarPerfil/MusicaReciente/MusicaReciente';
import ActividadFisica from '../EditarPerfil/ActividadFisica/ActividadFisica';
import GenerosFavoritos from '../EditarPerfil/GenerosFavoritos/GenerosFavoritos';
import InteraccionSocial from '../EditarPerfil/InteraccionSocial/InteraccionSocial';
import ControladorFotos from '../EditarPerfil/ControladorFotos/ControladorFotos';

const EditarPerfil = () => {
  return (
    <> 
      <SideNav />
      <Titulo />
      <div className="editar-perfil-container">
           <Perfil />
           <Genero />
           <DescripcionTextarea />
           <MusicaReciente />
           <ActividadFisica />
           <GenerosFavoritos />
           <InteraccionSocial />
      </div>
      <ControladorFotos />
    </>
  );
};

export default EditarPerfil;
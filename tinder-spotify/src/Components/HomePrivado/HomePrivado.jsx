import React from 'react'
import './HomePrivado.css';

import TituloHome from '../../Components/HomePrivado/TituloHome/TituloHome';
import SideNav from '../Nav/SideNav/SideNav';
import PerfiHome from '../HomePrivado/PerfiHome/PerfiHome';
import GeneroHome from '../HomePrivado/GeneroHome/GeneroHome';
import DescripHome from '../HomePrivado/DescripHome/DescripHome';
import MisCanciones from '../HomePrivado/MisCanciones/MisCanciones';
import Preferencias from '../HomePrivado/Preferencias/Preferencias';
import PrincipalFoto from '../HomePrivado/PrincipalFoto/PrincipalFoto';

const HomePrivado = () => {
  return (
    <>
      <div className="contedor-pri">
          <SideNav/>
          <TituloHome />
          <div className="Home-container">
            <PerfiHome />
            <GeneroHome />
            <DescripHome />
            <MisCanciones />
            <Preferencias />
          </div>
            <PrincipalFoto />
      </div>
    </>
  )
}

export default HomePrivado;
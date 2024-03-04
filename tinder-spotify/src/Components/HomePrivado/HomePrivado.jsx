import React, { useState } from 'react';
import './HomePrivado.css';

import TituloHome from '../../Components/HomePrivado/TituloHome/TituloHome';
import PreferentialSettings from '../../Components/Nav/preferentialSettings/preferentialSettings';
import SideNav from '../Nav/SideNav/SideNav';

//Persona1
import PerfiHome1 from './Persona1/PerfiHome1/PerfiHome';
import GeneroHome1 from './Persona1/GeneroHome1/GeneroHome';
import DescripHome1 from './Persona1/DescripHome1/DescripHome';
import MisCanciones1 from './Persona1/MisCanciones1/MisCanciones';
import Preferencias1 from './Persona1/Preferencias1/Preferencias';
import PrincipalFoto1 from './Persona1/PrincipalFoto1/PrincipalFoto';

//Persona2
import PerfiHome2 from './Persona2/PerfiHome2/PerfiHome2';;
import GeneroHome2 from './Persona2/GeneroHome2/GeneroHome2';
import DescripHome2 from './Persona2/DescripHome2/DescripHome2';
import MisCanciones2 from './Persona2/MisCanciones2/MisCanciones2';
import Preferencias2 from './Persona2/Preferencias2/Preferencias2';
import PrincipalFoto2 from './Persona2/PrincipalFoto2/PrincipalFoto2';

const HomePrivado = () => {
  const [modalOpen, setModalOpen] = useState(false);
  const [mostrarPersona1, setMostrarPersona1] = useState(true);

  const toggleModal = () => {
    setModalOpen(!modalOpen);
  };

  const togglePrincipalFoto = () => {
    setMostrarPersona1((prev) => !prev);
  };

  return (
    <>
      <div className="contedor-pri ">
          <SideNav/>
           <TituloHome onFilterClick={toggleModal} />
           {modalOpen && <PreferentialSettings  onClose={toggleModal}/>}
           <div className="match-profile">
            <div className="Home-container">
              {mostrarPersona1 ? (
                <>
                  <PerfiHome1 />
                  <GeneroHome1 />
                  <DescripHome1 />
                  <MisCanciones1 />
                  <Preferencias1 />
                </>
              ) : (
                <>
                  <PerfiHome2 />
                  <GeneroHome2 />
                  <DescripHome2 />
                  <MisCanciones2 />
                  <Preferencias2 />
                </>
              )}
              </div>
              {mostrarPersona1 ? (
              <PrincipalFoto1 onNextClick={togglePrincipalFoto} />
              ) : (
              <PrincipalFoto2 onPrevClick={togglePrincipalFoto} />
              )}
            </div>
          </div>
    </>
  )
}

export default HomePrivado;
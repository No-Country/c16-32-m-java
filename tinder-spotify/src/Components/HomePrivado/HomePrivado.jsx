import './HomePrivado.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import TituloHome from '../../Components/HomePrivado/TituloHome/TituloHome';
import SideNav from '../Nav/SideNav/SideNav';
import PerfiHome from '../HomePrivado/PerfiHome/PerfiHome';
import GeneroHome from '../HomePrivado/GeneroHome/GeneroHome';
import DescripHome from '../HomePrivado/DescripHome/DescripHome';
import MisCanciones from '../HomePrivado/MisCanciones/MisCanciones';
import Preferencias from '../HomePrivado/Preferencias/Preferencias';
import PrincipalFoto from '../HomePrivado/PrincipalFoto/PrincipalFoto';

const HomePrivado = () => {
  const tokenChatBeat = localStorage.getItem("token-ChatBeat");
  const userId = localStorage.getItem("userId");
  const [user, setUser]= useState({});

  const getLoggedUser = async () => {
    const userLogged = await axios.get(
      "http://localhost:8080/users/id/" + userId, 
      {
        headers: { Authorization: 'Bearer ' + tokenChatBeat }
      });
      console.log(userLogged.data);
    setUser(userLogged.data);
  }

  useEffect(() => { getLoggedUser(); },[])

  return (
    <>
      <div className="contedor-pri ">
          <SideNav/>
          <TituloHome />
          <div className="Home-container">
          <PerfiHome name={user.name} birthdate={user.birthdate}/>
          <GeneroHome gender={user.gender} pronouns={user.pronouns}/>
          <DescripHome description={user.description}/>
          <MisCanciones currentSong={user.currentSong}/>
          <Preferencias />
          </div>
          <PrincipalFoto />
</div>
    </>
  )
}

export default HomePrivado;
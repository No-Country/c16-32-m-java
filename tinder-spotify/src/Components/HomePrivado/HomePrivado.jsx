import "./HomePrivado.css";
import axios from "axios";
import React, { useState, useEffect } from "react";
import TituloHome from "../../Components/HomePrivado/TituloHome/TituloHome";
import PreferentialSettings from "../../Components/Nav/preferentialSettings/preferentialSettings";
import SideNav from "../Nav/SideNav/SideNav";
import PerfiHome from "../HomePrivado/PerfiHome/PerfiHome";
import GeneroHome from "../HomePrivado/GeneroHome/GeneroHome";
import DescripHome from "../HomePrivado/DescripHome/DescripHome";
import MisCanciones from "../HomePrivado/MisCanciones/MisCanciones";
import Preferencias from "../HomePrivado/Preferencias/Preferencias";

//Persona1
import PrincipalFoto1 from "./Persona1/PrincipalFoto1/PrincipalFoto";

//Persona2
import PrincipalFoto2 from "./Persona2/PrincipalFoto2/PrincipalFoto2";

const HomePrivado = () => {
  const tokenChatBeat = localStorage.getItem("token-ChatBeat");
  const userId = localStorage.getItem("userId");
  const [user, setUser] = useState({});
  const [modalOpen, setModalOpen] = useState(false);
  const [mostrarPersona1, setMostrarPersona1] = useState(true);
  const matchedPerson = {
    name: "Anna Karenina",
    birthdate: "1991-08-14",
    gender: "FEMENINO",
    pronouns: "ella",
    descrpition: "Soy una apasionada de la vida, siempre buscando nuevas experiencias y conexiones genuinas. Amo sumergirme en el arte, la literatura y la música; son las cosas que hacen latir mi corazón más fuerte.",
    currentSong: "Todos me miran - Gloria Trevi", 
  }

  const getLoggedUser = async () => {
    const userLogged = await axios.get(
      "http://localhost:8080/users/id/" + userId,
      {
        headers: { Authorization: "Bearer " + tokenChatBeat },
      }
    );
    console.log(userLogged.data);
    setUser(userLogged.data);
  };

  useEffect(() => {
    getLoggedUser();
  }, []);

  const toggleModal = () => {
    setModalOpen(!modalOpen);
  };

  const togglePrincipalFoto = () => {
    setMostrarPersona1((prev) => !prev);
  };

  return (
    <>
      <div className="contedor-pri ">
        <SideNav />
        <TituloHome onFilterClick={toggleModal} />
        {modalOpen && <PreferentialSettings onClose={toggleModal} />}
        <div className="Home-container">
          {mostrarPersona1 ? (
            <>
              <PerfiHome name={user.name} birthdate={user.birthdate} />
              <GeneroHome gender={user.gender} pronouns={user.pronouns} />
              <DescripHome description={user.description} />
              <MisCanciones currentSong={user.currentSong} />
              <Preferencias />
            </>
          ) : (
            <>
              <PerfiHome name={matchedPerson.name} birthdate={matchedPerson.birthdate} />
              <GeneroHome gender={matchedPerson.gender} pronouns={matchedPerson.pronouns} />
              <DescripHome description={matchedPerson.description} />
              <MisCanciones currentSong={matchedPerson.currentSong} />
              <Preferencias />
            </>
          )}
        </div>
        {mostrarPersona1 ? (
          <PrincipalFoto1 onNextClick={togglePrincipalFoto} />
        ) : (
          <PrincipalFoto2 onPrevClick={togglePrincipalFoto} />
        )}
      </div>
    </>
  );
};

export default HomePrivado;

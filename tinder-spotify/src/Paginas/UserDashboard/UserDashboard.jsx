import "./UserDashboard.css";
import axios from "axios";
import React, { useState, useEffect } from "react";
import TituloHome from "../../Components/HomePrivado/TituloHome/TituloHome";
import SideNav from "../../Components/Nav/SideNav/SideNav";
import PerfiHome from "../../Components/HomePrivado/Persona1/PerfiHome1/PerfiHome";
import GeneroHome from "../../Components/HomePrivado/Persona1/GeneroHome1/GeneroHome";
import DescripHome from "../../Components/HomePrivado/Persona1/DescripHome1/DescripHome";
import MisCanciones from "../../Components/HomePrivado/Persona1/MisCanciones1/MisCanciones";
import Preferencias from "../../Components/HomePrivado/Persona1/Preferencias1/Preferencias";

//PersonaLoggeada
import PrincipalFoto from "../../Components/HomePrivado/PrincipalFoto/PrincipalFoto";

const UserDashboard = () => {
  const tokenChatBeat = localStorage.getItem("token-ChatBeat");
  const userId = localStorage.getItem("userId");
  const [user, setUser] = useState({});
  const [mostrarPersona1, setMostrarPersona1] = useState(true);

 
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

  const togglePrincipalFoto = () => {
    setMostrarPersona1((prev) => !prev);
  };

  return (
    <>
      <div className="contedor-pri ">
        <SideNav />
        <TituloHome />
        <div className="Home-container">
              <PerfiHome name={user.name} birthdate={user.birthdate} />
              <GeneroHome gender={user.gender} pronouns={user.pronouns} />
              <DescripHome description={user.description} />
              <MisCanciones currentSong={user.currentSong} />
              <Preferencias />
        </div>
          <PrincipalFoto onNextClick={togglePrincipalFoto} />
      </div>
    </>
  );
};

export default UserDashboard;

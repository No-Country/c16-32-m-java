import "../../Components/HomePrivado/PerfiHome/PerfiHome.css";
import axios from "axios";
import React, { useState, useEffect } from "react";
// import TituloHome from "../../Components/HomePrivado/TituloHome/TituloHome";
import SideNav from "../../Components/Nav/SideNav/SideNav";
import PerfiHome from "../../Components/HomePrivado/PerfiHome/PerfiHome";
import GeneroHome from "../../Components/HomePrivado/GeneroHome/GeneroHome";
import DescripHome from "../../Components/HomePrivado/DescripHome/DescripHome";
import MisCanciones from "../../Components/HomePrivado/MisCanciones/MisCanciones";
import Preferencias from "../../Components/HomePrivado/Preferencias/Preferencias";
import TituloUserD from './TituloUserD/TituloUserD'
//PersonaLoggeada
import PrincipalFoto from "../../Components/HomePrivado/PrincipalFoto/PrincipalFoto";
import FotosUserDashboard from "../../Components/HomePrivado/PrincipalFoto/FotosUserDashboard";

const UserDashboard = ({onChange}) => {
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
        <SideNav  onChange={onChange}/>
        <div className="main-and-menu">
          <TituloUserD/>
          <div className="match-profile">
            <div className="Home-container">
              <PerfiHome name={user.name} birthdate={user.birthdate} />
              <GeneroHome gender={user.gender} pronouns={user.pronouns} />
              <DescripHome description={user.description} />
              <MisCanciones currentSong={user.currentSong} />
              <Preferencias />
            </div>
            <FotosUserDashboard onNextClick={togglePrincipalFoto} />
          </div>
        </div>
      </div>
    </>
  );
};

export default UserDashboard;

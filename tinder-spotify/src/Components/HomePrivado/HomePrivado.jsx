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
  const [userMatches, setUserMatches] = useState([]);
  const [selectedMatch, setSelectedMatch] = useState({});
  const [selectedUser, setSelectedUser] = useState();
  const [modalOpen, setModalOpen] = useState(false);
  const [indexActualMatch, setIndexActualMatch] = useState(0);

  const getUserMatches = async () => {
    const userMatches = await axios.get(
      "http://localhost:8080/matches/users/" + userId,
      {
        headers: { Authorization: "Bearer " + tokenChatBeat },
      }
    );
    console.log(userMatches.data.content);
    setUserMatches(userMatches.data.content);
    setSelectedMatch(userMatches.data.content[0]);
    getMatchedUser(userMatches.data.content[0]);
  };

  useEffect(() => {
    getUserMatches();
  }, []);

  const toggleModal = () => {
    setModalOpen(!modalOpen);
  };

  const nextMatch = () => {
    let newIndex = indexActualMatch + 1;
    if (newIndex == 2) { newIndex = 0; }
    setIndexActualMatch(newIndex);

    const actualIndexMatch = userMatches.findIndex((match) => {
      return match.matchId == selectedMatch.matchId
    })

    let newIndexMatches = actualIndexMatch + 1;
    if (newIndexMatches == userMatches.length) {
      newIndexMatches = 0;
    }

    const newMatch = userMatches[newIndexMatches];
    setSelectedMatch(newMatch);
    getMatchedUser(newMatch);
  };

  const previousMatch = () => {
    let newIndex = indexActualMatch - 1;
    if (newIndex < 0) { newIndex = 1; }
    setIndexActualMatch(newIndex);

    const actualIndexMatch = userMatches.findIndex(
      (match) => {
        return match.matchId == selectedMatch.matchId
      })

    let newIndexMatches = actualIndexMatch - 1;
    if (newIndexMatches < 0) {
      newIndexMatches = userMatches.length - 1;
    }

    const newMatch = userMatches[newIndexMatches];
    setSelectedMatch(newMatch);
    getMatchedUser(newMatch);
  };

  const getMatchedUser = (selectedMatch) => {
    if (selectedMatch.user1.userId == userId) {
      setSelectedUser(selectedMatch.user2);
    }
    else setSelectedUser(selectedMatch.user1);
  };


  return (
    <>

    <div className="contedor-pri ">
        <SideNav className="col-1" />
        <div className="main-and-menu col-11">
            <TituloHome onFilterClick={toggleModal} />
            <div className="match-profile">
                {modalOpen && <PreferentialSettings onClose={toggleModal} />}
                {selectedUser && (

                    <div className="Home-container">
                       <p>{selectedMatch.compatibilityPercentage}</p>
                        <PerfiHome name={selectedUser.name} birthdate={selectedUser.birthdate} />
                        <GeneroHome gender={selectedUser.gender} pronouns={selectedUser.pronouns} />
                        <DescripHome description={selectedUser.description} />
                        <MisCanciones currentSong={selectedUser.currentSong} />
                        <Preferencias />
                    </div>
                )}
                {indexActualMatch == 0 && (
                    <PrincipalFoto1 onNextClick={nextMatch} />)}
                {indexActualMatch == 1 && (
                    <PrincipalFoto2 onPrevClick={previousMatch} />)}

            </div>
        </div>
    </div>
</>
  );
};

export default HomePrivado;

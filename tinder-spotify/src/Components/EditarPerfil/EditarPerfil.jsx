import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './EditarPerfil.css';
import { useNavigate } from 'react-router-dom';
import Titulo from '../EditarPerfil/Titulo/Titulo';
import SideNav from '../Nav/SideNav/SideNav';
import Perfil from '../EditarPerfil/Perfil/Perfil';
import Genero from '../EditarPerfil/Genero/Genero';
import DescripcionTextarea from '../EditarPerfil/DescripcionTextarea/DescripcionTextarea';
import MusicaReciente from '../EditarPerfil/MusicaReciente/MusicaReciente';
import ActividadFisica from '../EditarPerfil/ActividadFisica/ActividadFisica';
import GenerosFavoritos from '../EditarPerfil/GenerosFavoritos/GenerosFavoritos';
import InteraccionSocial from '../EditarPerfil/InteraccionSocial/InteraccionSocial';
// import BotonMe from '../../Components/Button/BotonMediano/BotonMe';
import ControladorFotos from '../EditarPerfil/ControladorFotos/ControladorFotos';

const EditarPerfil = () => {
  const tokenChatBeat = localStorage.getItem("token-ChatBeat");
  const userId = localStorage.getItem("userId");
  const [name, setName] = useState('');
  const [birthdate, setBirthdate] = useState('');
  const [gender, setGender] = useState('');
  const [pronouns, setPronouns] = useState('');
  const [description, setDescription] = useState('');
  const [socialBattery, setSocialBattery] = useState('');
  const [currentSong, setCurrentSong] = useState('');
  const [alert, setAlert] = useState('');
  const navigate = useNavigate();

  const getLoggedUser = async () => {
    const userLogged = await axios.get(
      "http://localhost:8080/users/id/" + userId,
      {
        headers: { Authorization: 'Bearer ' + tokenChatBeat }
      });
    console.log(userLogged.data);
    setName(userLogged.data.name);
    localStorage.setItem("username", userLogged.data.name);
    localStorage.setItem("currentSong", userLogged.data.currentSong);
    setBirthdate(userLogged.data.birthdate);
    setGender(userLogged.data.gender);
    setPronouns(userLogged.data.pronouns);
    setDescription(userLogged.data.description);
    setSocialBattery(userLogged.data.socialBattery);
    setCurrentSong(userLogged.data.currentSong);
  }

  useEffect(() => { getLoggedUser(); }, [])

  const handleEditProfile = async () => {
    try {
      const response = await axios.put(
        "http://localhost:8080/users/id/" + userId, {
        userId: userId,
        name: name,
        gender: gender,
        pronouns: pronouns,
        description: description,
        birthdate: birthdate,
        socialBattery: socialBattery,
        currentSong: currentSong,
      },
        {
          headers: { Authorization: 'Bearer ' + tokenChatBeat }
        });
      console.log(response.data);
      console.log('Respuesta del servicio externo:', response.data);

      localStorage.setItem("username", name);

      navigate('/user-dashboard');
    } catch (error) {
      console.error(error);
      setAlert('Hubo un error al editar perfil. Por favor, inténtalo de nuevo.');
    }
  };

  return (
    <>
      <div className="cont-pri-perf ">
        <SideNav className="col-1" />
        <div className='menu-and-profile col-11'>
          <Titulo />
          <div className="editar-perfil-container">
            <Perfil name={name} onNameChange={setName}
              birthdate={birthdate} onBirthdateChange={setBirthdate} />
            <Genero gender={gender} onGenderChange={setGender}
              pronouns={pronouns} onPronounsChange={setPronouns} />
            <DescripcionTextarea description={description}
              onDescriptionChange={setDescription} />
            <i className="bi bi-music-note-list"> Última canción escuchada</i> <p><em>{currentSong}</em></p>
            <ActividadFisica />
            <GenerosFavoritos />
            <InteraccionSocial socialBattery={socialBattery}
              onSocialBatteryChange={setSocialBattery} />
            {alert && (<p>{alert}</p>)}
            <button onClick={handleEditProfile} className='save-changes' texto="Guardar">Guardar </button>
          </div>

          <ControladorFotos />
        </div>
      </div>
    </>
  );
};

export default EditarPerfil;
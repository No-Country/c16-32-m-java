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
import BotonMe from '../../Components/Button/BotonMediano/BotonMe';
import ControladorFotos from '../EditarPerfil/ControladorFotos/ControladorFotos';

const EditarPerfil = () => {
    const tokenChatBeat = localStorage.getItem("token-ChatBeat");
    const userId = localStorage.getItem("userId");
    const [name, setName] =useState('');
    const [birthdate, setBirthdate] =useState('');
    const [gender, setGender] =useState('');
    const [pronouns, setPronouns] =useState('');
    const [description, setDescription] =useState('');
    const [socialBattery, setSocialBattery] =useState('');
    const [currentSong, setCurrentSong] =useState('');
    
  
    const handleEditProfile = async () => {
      try {
        const response = await axios.put(
          "http://localhost:8080/users/id/" + userId, {
          userId : userId,
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
        
        navigate('/home-privado');
      } catch (error) {
        setAlert('Hubo un error al editar perfil. Por favor, inténtalo de nuevo.');
      }
    };

  return (
    <> 
        <div className="cont-pri-perf">
          <SideNav />
          <Titulo />
          <div className="editar-perfil-container">
              <Perfil name={name} onNameChange={setName}
              birthdate={birthdate} onBirthdateChange={setBirthdate}/>
              <Genero gender={gender} onGenderChange={setGender}
                  pronouns={pronouns} onPronounsChange={setPronouns} />
              <DescripcionTextarea description={description}
                      onDescrptionChange={setDescription} />
              <MusicaReciente currentSong={currentSong} 
              onCurrentSongChange={setCurrentSong}/>
              <ActividadFisica />
              <GenerosFavoritos />
              <InteraccionSocial socialBattery={socialBattery} 
              onSocialBattery={setSocialBattery} />
          </div>
          <Button onClick={handleEditProfile} > Guardar cambios! </Button>
          <BotonMe texto="Guardar"/>
          <ControladorFotos />
        </div>
    </>
  );
};

export default EditarPerfil;
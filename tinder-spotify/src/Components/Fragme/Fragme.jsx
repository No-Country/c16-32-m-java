import React from 'react';
import log from '../../assets/log.png';
import './Fragme.css'; 

const Fragme = () => {
  return (
    <div className="fragme-container">
      <div className="fragme-icon">
        <img src={log} alt="" />
      </div>
      <div className="fragme-text-container">
        <p className="fragme-text">ChatBeat es una plataforma diseñada para ayudarte a encontrar conexiones profundas en el amor o la amistad, conectándote con personas que comparten tus gustos musicales. Al identificar afinidades musicales, facilitamos la creación de relaciones auténticas y significativas. Ya sea que estés buscando un compañero de conciertos o alguien con quien compartir tus canciones favoritas, nuestra aplicación te ofrece la oportunidad de conectar con personas que comparten tu pasión por la música. ¡Únete a nuestra comunidad y comienza a descubrir nuevas conexiones emocionantes hoy mismo!</p>
      </div>
    </div>
  );
}

export default Fragme;
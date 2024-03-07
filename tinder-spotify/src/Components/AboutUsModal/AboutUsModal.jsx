import React from 'react';
import PropTypes from 'prop-types';
import './AboutUsModal.css';

import log from '../../assets/log.png';
import musica from '../../assets/musica.png';


const AboutUsModal = ({ onClose }) => {
  return (
      <div className="about-us-modal">
          <div className="about-us-modal-content">
              <span className="about-us-modal-close" onClick={onClose}>&times;</span>
              <div className="about-us-modal-body">
                  <div className="about-us-modal-icon">
                  <img className="img-logo" />
                  </div>
                  <h2 className="about-us-modal-title">Sobre Nosotros</h2>
                  <div className="about-us-modal-paragraph-container">
                      <p>En ChatBeat, creemos que la música es la clave para conectar a un nivel más profundo. ¿Cómo suena tu historia de amor? Descúbrelo al conectar con personas fuera de tu círculo de conocidos. Rompe las barreras y sumérgete en un mundo donde la melodía de la vida se entrelaza con las notas de la pasión.</p>
                      <p>La música es un lenguaje universal que va más allá de las palabras. En ChatBeat, te ofrecemos la oportunidad de conocer a alguien desde una perspectiva única. Descubre cómo procesan las emociones, cómo expresan sus sentimientos a través de la música y crea conexiones auténticas que trascienden lo superficial.</p>
                      <p>Encuentra la Armonía en lo Desconocido: Conéctate a Nivel Profundo</p>
                  </div>
                  <div className="about-us-modal-bottom-icon">
                      <img className='charge-img' src={musica} alt="barra de carga chatBeat" />
                  </div>
              </div>
          </div>
      </div>
  );
};

AboutUsModal.propTypes = {
  onClose: PropTypes.func.isRequired,
};

export default AboutUsModal;
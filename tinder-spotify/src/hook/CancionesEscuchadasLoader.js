import React, { useEffect } from 'react';
import axios from 'axios';

const CancionesEscuchadasLoader = ({ onCancionesCargadas }) => {
  useEffect(() => {
    const cargarCancionesEscuchadas = async () => {
      try {
        // Realizar la solicitud HTTP utilizando Axios a la API de Spotify
        const response = await axios.get('URL_DE_LA_API_DE_SPOTIFY');
        if (response.status === 200) {
          const data = response.data;
          onCancionesCargadas(data);
        } else {
          throw new Error('Error al cargar las canciones escuchadas');
        }
      } catch (error) {
        console.error(error);
      }
    };

    cargarCancionesEscuchadas();
  }, [onCancionesCargadas]);

  return null; 
};

export default CancionesEscuchadasLoader;
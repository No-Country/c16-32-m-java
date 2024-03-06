import React, { useState } from 'react';
import './DescripcionTextarea.css';

const DescripcionTextarea = () => {
  const [descripcion, setDescripcion] = useState("Café de origen, vino tinto, cerveza artesanal, pueblos por visitar y buenas conversaciones, es lo que disfruto mucho y me encantaría conocer quien también goce de estos planes !");

  const handleTextareaChange = (event) => {
    setDescripcion(event.target.value);
  };

  const handleTextareaClick = () => {
    setDescripcion('');
  };

  const handleBlur = () => {
    if (descripcion === '') {
      setDescripcion("Café de origen, vino tinto, cerveza artesanal, pueblos por visitar y buenas conversaciones, es lo que disfruto mucho y me encantaría conocer quien también goce de estos planes !");
    }
  };

  return (
    <label className="input-label-com">
      Acerca de mi:
      <textarea
        className="componte-tex"
        maxLength={350}
        onClick={handleTextareaClick}
        onBlur={handleBlur}
        onChange={handleTextareaChange} 
        value={descripcion}
      ></textarea>
    </label>
  );
};

export default DescripcionTextarea;
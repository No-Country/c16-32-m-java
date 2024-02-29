import React, { useState } from 'react';
import './DescripcionTextarea.css';


const DescripcionTextarea = ({ description, 
  setDescription }) => {

  const handleTextareaClick = () => {
    setDescription(description);
  };

  const handleBlur = () => {
    if (descripcion === '') {
      setDescripcion("Escribe algo interesante!");
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
        value={description}
      ></textarea>
    </label>
  );
};

export default DescripcionTextarea;
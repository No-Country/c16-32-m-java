import React, { useState } from 'react';
import './DescripcionTextarea.css';


const DescripcionTextarea = ({ description, 
  onDescriptionChange }) => {

  const handleTextareaClick = (value) => {
    onDescriptionChange(value);
  };

  return (
    <label className="input-label-com">
      Acerca de mi:
      <textarea
        className="componte-tex"
        maxLength={350}
        onChange={(event)=>{handleTextareaClick(event.target.value)}}
        value={description}
      ></textarea>
    </label>
  );
};

export default DescripcionTextarea;
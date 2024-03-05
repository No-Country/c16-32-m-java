import React from 'react';
import './DescripHome.css';

const DescripHome = ({description}) => {
  return (
    <div className="descrip-home-container">
      <h2 className="descrip-title">Sobre mí</h2>
      <p className="descrip-text">
        {description}
      </p>
    </div>
  );
}

export default DescripHome;
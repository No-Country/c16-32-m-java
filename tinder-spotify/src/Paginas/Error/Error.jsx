import React from 'react'
import { Link } from 'react-router-dom';
import chicaauriculares from '../../assets/chicaauriculares.png';
import './Error.css';


const Error = () => {
    return (
        <div>
        <div className='container'>
            <div className='div1'>
            <p className='letter'>Lo sentimos</p>
            <p className='letter2'>404 error</p>
            <p className='letter2'>Lo que buscas no esta disponible en este momento</p>
            <Link to="/">
            <button className='logout-button'>Volver al inicio</button>
            </Link>
            </div>
            <div className='div2'>
            <img src={chicaauriculares} alt="chicaauriculares" className='chica'/>
            </div>
        </div>
        </div>
      );
    };

export default Error;
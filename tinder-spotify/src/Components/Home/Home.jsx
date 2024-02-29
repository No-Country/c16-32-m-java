import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

import Nav from '../../Components/Nav/Nav';
import BotonPin from '../Button/BotonPrincipal/BotonPin';
import DescargaApp from '../Seccion/DescargaApp/DescargaApp';
import CardInfe from '../../Components/Cards/CardInfe/CardInfe';
import Fragme from '../Fragme/Fragme';
import Footer from '../../Components/Footer/Footer';

import banner from '../../assets/banner.png';
import fondo from '../../assets/fondo.png';


const Home = () => {
    return (
        <> 
        <div className='background-home'> 
        <Nav />
        <div className="container"> 
            <div className="card">
                <div className="card-images"> 
                    <img src={banner} alt="Imagen 1" />
                </div>
                <p className="paragraph">La música une corazones en una conexión perdurable</p> 
            </div>
            <img src={fondo} alt="Fondo" className="imagen-adicional" />
        </div>
        <div className="boton-container">
            <Link to="crear-cuenta">
              <BotonPin texto="Crear Cuenta" />
            </Link>
        </div>
        <DescargaApp />
        <CardInfe />
        <Fragme />
        <Footer />
        </div>
      </>
    );
}
  
export default Home;
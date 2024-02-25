import React from 'react';
import { Link } from 'react-router-dom';
import banner from '../../assets/banner.png';
import Nav from '../../Components/Nav/Nav';
import BotonPin from '../Button/BotonPrincipal/BotonPin';
import DescargaApp from '../Seccion/DescargaApp/DescargaApp';
import CardInfe from '../../Components/Cards/CardInfe/CardInfe';
import Fragme from '../Fragme/Fragme';
import Footer from '../../Components/Footer/Footer';

import './Home.css';

const Home = () => {
    return (
        <>  
        <Nav />
        <div className="container"> 
            <div className="card">
            <div className="card-images"> 
                <img src={banner} alt="Imagen 1" />
            </div>
            <p className="paragraph">La música une corazones en una conexión perdurable</p> 
            </div>
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
      </>
    );
}
  
export default Home;
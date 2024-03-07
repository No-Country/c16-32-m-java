import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Components/Home/Home';
import Login from './Paginas/Login/Login';
import EmailLogin from './Paginas/EmailLogin/EmailLogin';
import RecuperarContraseña from './Paginas/EmailLogin/RecuperarContraseña'
import NuevaContraseña from './Paginas/EmailLogin/NuevaContraseña';
import CrearCuentaConCelular from './Paginas/CrearCuentaConCelular/CrearCuentaConCelular';
import CrearCuenta from './Paginas/CrearCuenta/CrearCuenta';
import Logear from './Paginas/Logear/Logear';
import Codigo from './Paginas/Logear/Codigo/Codigo';
import Error from './Paginas/Error/Error';
import Chat from './Paginas/Chat/Chat';
import HomePrivado from './Components/HomePrivado/HomePrivado';
import EditarPerfil from './Components/EditarPerfil/EditarPerfil'
import UserDashboard from './Paginas/UserDashboard/UserDashboard';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap-icons/font/bootstrap-icons.css';


const App = () => {
    return (
        <Router>
            <div className='dark'>
                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/email-login" element={<EmailLogin />} />
                    <Route path="/recuperar-contraseña" element={<RecuperarContraseña />} />
                    <Route path="/nueva-contraseña" element={<NuevaContraseña />} />
                    <Route path="/crear-cuenta-con-celular" element={<CrearCuentaConCelular />} />
                    <Route path="/crear-cuenta" element={<CrearCuenta />} />
                    <Route path="/logear" element={<Logear />} />
                    <Route path="/codigo" element={<Codigo />} />
                    <Route path="/error" element={<Error />} />
                    <Route path="/home-privado" element={<HomePrivado />} />
                    <Route path="/editar-perfil" element={<EditarPerfil />} />
                    <Route path="/user-dashboard" element={<UserDashboard />} />
                    <Route path="/chat" element={<Chat />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
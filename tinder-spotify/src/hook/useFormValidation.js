import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function useFormValidation() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [repeatPassword, setRepeatPassword] = useState('');
  const [alert, setAlert] = useState('');
  const navigate = useNavigate();

  const handleContinue = () => {
    if (email.trim() === '' || password.trim() === '' || repeatPassword.trim() === '') {
        setAlert('Por favor, completa todos los campos.');
      } else {
        if (password !== repeatPassword) {
          setAlert('Las contraseñas no coinciden.');
        } else {
          navigate('/codigo');
        }
      }
  };

  return {
    email,
    setEmail,
    password,
    setPassword,
    repeatPassword,
    setRepeatPassword,
    alert,
    handleContinue,
  };
}

export default useFormValidation;
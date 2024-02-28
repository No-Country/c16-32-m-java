import { useState } from 'react';

const useInputValidation = () => {
  const [alert, setAlert] = useState('');

  const validateInputs = (inputs) => {
    const invalidInputs = inputs.filter(input => input === '');
    if (invalidInputs.length > 0) {
      setAlert('Por favor, completa todos los campos.');
      return false;
    } else {
      setAlert('');
      return true;
    }
  };

  return { alert, validateInputs };
};

export default useInputValidation;
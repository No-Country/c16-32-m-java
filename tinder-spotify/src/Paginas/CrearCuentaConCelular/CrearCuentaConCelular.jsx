import React, { useState } from 'react';
import CrearStep1 from './CrearStep1/CrearStep1';
import CrearStep2 from './CrearStep2/CrearStep2';


const CrearCuentaConCelular = () => {
    const [step, setStep] = useState(1);

    const handleContinue = () => {
        setStep(step + 1);
    };

    return (
        <div>
            {step === 1 ? <CrearStep1 handleContinue={handleContinue} /> : <CrearStep2 handleContinue={handleContinue} />}
        </div>
    );
};

export default CrearCuentaConCelular;
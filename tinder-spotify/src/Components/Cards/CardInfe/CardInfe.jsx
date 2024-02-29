import React from 'react';
import './CardInfe.css';

import card1 from '../../../assets/card1.png';
import card2 from '../../../assets/card2.png';
import card3 from '../../../assets/card3.png';

const CardInfe = () => {
  return (
    <div className="CardInfeContainer">
      <div className="CardInfeItem">
        <img src={card1} alt="Imagen 1" />
        <p>ChatBeat te permite enviar una canción <br/>
        con tu primer mensaje, rompiendo el hielo de <br/> 
        manera original. ¿Listo para conectar a <br/> 
        través de la música?</p>
      </div>
      <div className="CardInfeItem2">
        <img src={card2} alt="Imagen 2" />
        <p>ChatBeat es una solución multiplataforma,<br/> 
        es el lugar perfecto para conectar con <br/> 
        personas afines a tus gustos musicales. <br/> 
        ¡Descubre nuevas amistades y comparte tu <br/>
        pasión por la música!</p>
      </div>
      <div className="CardInfeItem">
        <img src={card3} alt="Imagen 3" />
        <p>Coordina fácilmente con otras personas <br/>
         para asistir juntos a los próximos eventos <br/> 
         musicales en la ciudad. Ya no tendrás que ir <br/> 
         solo a tus conciertos favoritos. ¡Únete a <br/> 
         nuestra comunidad y empieza a disfrutar la <br/> 
         música en compañía!</p>
      </div>
    </div>
  );
}

export default CardInfe;
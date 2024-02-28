import { useStompClient, useSubscription } from "react-stomp-hooks";
import React, { useState } from "react";


/* Trabajar en el nombre del componente... lol.*/
export const PublishComponent = ({ chatId }) => {
    const stompClient = useStompClient();
    const [messages, setMessages] = useState([]);
    const [messageContent, setMessageContent] = useState('');
    //const authToken = localStorage.getItem('authToken');
    /* Genera cliente para usar hook.
    Genera mensaje con useState*/

    useSubscription(`/topic/chat/${chatId}`, (message) => {
      const parsedMessage = JSON.parse(message.body);
      setMessages((prevMessages) => [...prevMessages, parsedMessage]);
    });


    // Función para enviar un mensaje
    const publishMessage = () => {
      if (stompClient) {
        stompClient.publish({
          destination: `/app/chat/${chatId}`,  // Reemplaza chatId con el ID de tu chat específico
          body: JSON.stringify({
            content: messageContent,
            // Otros campos necesarios para tu mensaje
          }),
        });
      }
    };

    return (
      <div>
        <ul>
          {messages.map((msg, index) => (
            <li key={index}>{msg.content}</li>
          ))}
        </ul>
        <input
          type="text"
          placeholder="Type your message..."
          value={messageContent}
          onChange={(e) => setMessageContent(e.target.value)}
        />
        <button onClick={publishMessage}>Send</button>
      </div>
    );

    /*Publica en la url configurada desde back el mensaje ingresado.*/
    /*return (
      <>
      <input value={message} onChange={(value)=>{setMessage(value.target.value)}}/>
      <button onClick={publishMessage}> Send message </button>
      </>
      /*input toma los valores -mensaje- cuando cambia,
      button activa la función.*/
    //) 
}
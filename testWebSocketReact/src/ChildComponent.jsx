import { useState } from "react";
import { useSubscription } from "react-stomp-hooks";

export const ChildComponent = ({chatId}) => {

  const [message, setMessage] = useState([]);

  useSubscription(`/topic/chat/${chatId}`, (message) => {
    const parsedMessage = JSON.parse(message.body);
    setMessages((prevMessages) => [...prevMessages, parsedMessage]);
  });

  return (
    <>
      {message.map((m, i) => (
        <div key={i}> {m.lastMessage}</div>
      ))}
    </>
  );
};
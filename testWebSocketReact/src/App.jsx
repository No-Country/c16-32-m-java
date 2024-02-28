import {StompSessionProvider} from "react-stomp-hooks";
//import { ChildComponent } from "./ChildComponent";
import { PublishComponent } from "./PublishComponent";

function App() {
  //const authToken = localStorage.getItem('authToken');
  const chatId = 1;

  return (
    <StompSessionProvider
      url={'http://localhost:8080/ws'}
      >
      <PublishComponent 
      chatId={chatId}
      />
    </StompSessionProvider>
  )
}

export default App
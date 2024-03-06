import { useEffect, useState, useRef } from 'react';
import './Chat.css';
import '../../Components/StyleVariables/Color.css';
import axios from "axios";
import Nav from '../../Components/Nav/Nav';
import SideNav from "../../Components/Nav/SideNav/SideNav";
import SockJS from 'sockjs-client/dist/sockjs';
import { over } from 'stompjs';

export const Chat = () => {
    const [message, setMessage] = useState('');
    const [stompClient, setStompClient] = useState(null);

    const root = document.documentElement;
    const computedStyles = getComputedStyle(root);
    const backgroundColorChat = computedStyles.getPropertyValue('--windows-color');
    const primaryColor = computedStyles.getPropertyValue('--primary-color');
    const bannedColor = computedStyles.getPropertyValue('--terciary-color');
    const [chatId, setChatId] = useState(0);
    const [userId, setUserId] = useState(localStorage.getItem("userId"));
    const prevUsersRef = useRef([]);
    const [activeTab, setActiveTab] = useState(0);
    const [users, setUsers] = useState([
        {
            "id": 1,
            "chatId": 1,
            "name": "Roberto",
            "profilePicUrl": "https://res.cloudinary.com/dy7gwan0n/image/upload/v1709700051/2_rsermf.webp",
            "history": ["¿Cuál es tu canción favorita?", "Mmmm... Hubris, de Sevdaliza", "Ah, no la conozco. Ahí la escucho..."]
        }
    ]);

    const connectWebSocket = () => {
        const socket = new SockJS('http://localhost:8080/ws');
        const temp = over(socket);
        temp.connect({}, () => {
            setStompClient(temp);
        });
    };

    useEffect(() => {
        connectWebSocket();
    }, []);

    const sendMessage = (chatId) => {
        stompClient.send(`/app/send/${chatId}`, {}, message);
        setChatId(chatId);

        const usersCopy = [...users];
        const currentUser = usersCopy[activeTab];
        currentUser.history.push(message);
        setUsers(usersCopy);
        setMessage('');
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get("http://localhost:8080/chats/users/" + userId);
                const data = response.data.content;

                const transformedUsers = data.map((chat) => ({
                    id: chat.sender.userId,
                    name: chat.sender.name,
                    chatId: chat.chatId,
                    profilePicUrl: chat.sender.photo,
                    history: chat.previousMessages
                }));

                setUsers(transformedUsers);
            } catch (error) {
                console.error('Error al entrar a los chats:', error);
            }
        }

        if (JSON.stringify(prevUsersRef.current) !== JSON.stringify(users)) {
            fetchData();
            prevUsersRef.current = users;
        }
    }, [userId, users]);

    const handleTabClick = (index) => {
        setActiveTab(index);
    };

    return (
        <div className="mb-5 display-flex-row">
            <SideNav />
            <div className='chat-nav'>
                <Nav />
                {users.length > 0 && (
                    <div className="row mt-5">
                        <div className="col-md-12 col-lg-6 mb-3">
                            <div className="list-group" id="list-tab" role="tablist">
                                {users.map((user, index) => {
                                    const id = `list-${user.name.toLowerCase().replace(/\s+/g, '-')}`;
                                    return (
                                        <a
                                            key={index}
                                            className={`list-group-item list-group-item-action ${index === activeTab ? 'active' : ''}`}
                                            id={`${id}-list`}
                                            data-bs-toggle="list"
                                            href={`#${id}`}
                                            role="tab"
                                            aria-controls={id}
                                            onClick={() => handleTabClick(index)}
                                        >
                                            <img src={user.profilePicUrl} alt={user.name} className='profile-photo' /> <strong className='mx-1'>{user.name}</strong>
                                        </a>
                                    );
                                })}
                            </div>
                        </div>
                        <div className="col-md-12 col-lg-6 mb-3">
                            <div className="tab-content" id="nav-tabContent">
                                {users.map((user, index) => {
                                    const id = `list-${user.name.toLowerCase().replace(/\s+/g, '-')}`;
                                    return (
                                        <div key={index} className={`tab-pane fade ${index === activeTab ? 'show active' : ''}`} id={id} role="tabpanel" aria-labelledby={`${id}-list`}>
                                            <div className="card card-chat text-center">
                                                <div className="card-header color-card-header">
                                                    <div className="row">
                                                        <div className="col-8 text-start">
                                                            <img className="logo-image" src="/src/assets/log.png" alt="Logo" style={{ filter: 'brightness(0) invert(1)', width: "32px", height: "32px" }} />
                                                            <strong className='mx-3 name-user-header'>{user.name}</strong>
                                                        </div>
                                                        <div className='col-4 text-end'>
                                                            <button type="submit" className="btn btn-primary btn-sm banned-icon"><i className="bi bi-person-fill-slash"></i></button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="card-body card-body-scroll">
                                                    {users[activeTab].history.map((message, index) => (
                                                        <div key={index} className={`text-${index % 2 === 0 ? 'start' : 'end'}`}>
                                                            <div
                                                                className={`alert ${index % 2 === 0 ? 'alert-info message-sender-color' : 'alert-warning message-receiver-color'} d-inline-block`}
                                                                role="alert"
                                                            >
                                                                {message}
                                                            </div>
                                                        </div>
                                                    ))}
                                                </div>
                                                <hr />
                                                <div className="row g-3">
                                                    <div className="col-7 mx-5">
                                                        <input
                                                            className="form-control"
                                                            type="text"
                                                            value={message}
                                                            placeholder="Escriba su mensaje..."
                                                            onChange={(value) => { setMessage(value.target.value) }}
                                                        />
                                                    </div>
                                                    <div className="col-2">
                                                        <button type="submit" className="btn btn-primary btn-sm mb-3 button-send-message" onClick={() => sendMessage(users[activeTab].chatId)}><i className="bi bi-fast-forward-fill"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    );
                                })}
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    )
}

export default Chat;
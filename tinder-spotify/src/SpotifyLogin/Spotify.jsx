import { useEffect, useState } from "react";
import axios from "axios";
import { Buffer } from "buffer";

const Spotify = ({ onSpotifyLogin }) => {
    const [code, setCode] = useState("");  
    const [artists, setArtists] = useState([]);  
    const [userId, setUserId] = useState(0);
  
    const response_type ="code";
    const client_id = "String de client id";
    const scope = "user-read-private user-read-email user-follow-read";
    const redirect_uri = "http://localhost:5173";
    const client_secret = "String de client secret";
    const url = "https://accounts.spotify.com/authorize?response_type="+response_type+"&client_id=" + client_id + "&scope="+scope+"&redirect_uri=" + redirect_uri;
  
    const login = () => {
        const user = {
          "password" : "pedro123!",
          "email" : "email@hotmail.com",
        }
        console.log(user.email, user.password);
        axios.post("http://localhost:8080/users/login", user)
        .then((data)=>{
            console.log(data);
            setUserId(data.data.userId);
        });
    };
  
    const lookForArtists = () => {
      let accessToken = localStorage.getItem('token');
      axios.get('https://api.spotify.com/v1/me/following?type=artist&limit=50', {
        headers: {
          Authorization: 'Bearer ' + accessToken
        }
      }).then((result) =>{
        console.log("artists", result);
        setArtists(result.data.artists.items)
        const artistsToBack = artists.map((artist)=> {
          const artistToBack = {
            "artistId" : artist.id,
            "artistName" : artist.name,
          } 
          return artistToBack;
        }) 
        
        axios.post("http://localhost:8080/users/likedartists/"+userId, artistsToBack)
        .then((data)=>{
            console.log(data);
        });
  
      });
    }
  
    useEffect(() => {
      let code = window.localStorage.getItem("code");
  
      if (!code) {
        const qString = window.location.search;
        const urlSearch = new URLSearchParams(qString);
        code = urlSearch.get("code");
        if (code) {
          localStorage.setItem("code", code);
        }
      } else { 
          axios.post("https://accounts.spotify.com/api/token", {
              code: code,
              redirect_uri: redirect_uri,
              grant_type: "authorization_code",
          }, {
              headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                Authorization: "Basic " + new Buffer.from(client_id + ":" + client_secret).toString("base64"),
              },
          })
          .then((res) => {
              const accessToken = res.data.access_token;
              localStorage.setItem("token", accessToken);
  
              axios.get("https://api.spotify.com/v1/me", {
                  headers: {
                    Authorization: 'Bearer ' + accessToken
                  }
              }).then((result) =>{
                  const {display_name, email, images} = result.data;
                  const user = {
                    "name" : display_name,
                    "password" : "pedro123!",
                    "email" : email,
                    "birthdate" : "1990-04-02",
                    "photo" : images[0].url,
                    "gender": "MASCULINO", 
                    "pronouns" : "el",
                    "description" : "<3"
                  }
                  console.log(user.email, user.photo);
                  axios.post("http://localhost:8080/users/register", user)
                  .then((data)=>{
                      console.log(data);
                      setUserId(data.data.userId);
                      // Llamar a la función onSpotifyLogin después de completar la autenticación exitosa
                      onSpotifyLogin();
                  });
              });
          });
      }
      setCode(code);
    }, []);
  
    return (
      <>
        <a href={url}>Register</a>      
        <button onClick={login}>Login</button>
        <button onClick={lookForArtists}>Bring artists!</button>
  
        {artists && artists.map((artist,i) => (
        <p key={i}>{artist.name}</p>))}
      </>
    );
}
  
export default Spotify;
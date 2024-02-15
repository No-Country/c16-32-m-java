package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {

    @Value("${spring.security.oauth2.client.registration.spotify.redirect-uri}")
    private String spotifyRedirectUri;

    @GetMapping
    public ResponseEntity<String>
    handleSpotifyAuth(Authentication authentication){
        try{
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

            OAuth2User oauthUser = oauthToken.getPrincipal();

            String userDetailsJson = new ObjectMapper().writeValueAsString(oauthUser.getAttributes());

            String encodedUserDetails = URLEncoder.encode(userDetailsJson, StandardCharsets.UTF_8.toString());

            String redirectUrl = spotifyRedirectUri + "?details=" + encodedUserDetails;

            return ResponseEntity.status(302).header("Location", redirectUrl).body("Redirecting...");
        }catch (RuntimeException | UnsupportedEncodingException | JsonProcessingException e){
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}

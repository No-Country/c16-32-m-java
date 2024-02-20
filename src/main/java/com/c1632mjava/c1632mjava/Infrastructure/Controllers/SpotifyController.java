package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Entities.SpotifyUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/oauth/spotify")
@RequiredArgsConstructor
public class SpotifyController {

    private OAuth2AuthorizedClientService authorizedClientService;
    private RedirectStrategy redirectStrategy;
    private RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.provider.spotify.user-info-uri}")
    private String spotifyUserInfoUri;

    @GetMapping
    @PreAuthorize("permitAll()")
    public void handleSpotifyAuth(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken)
            throws IOException {
        if (authenticationToken != null) {
            OAuth2AuthorizedClient authorizedClient = authorizedClientService
                    .loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(),
                            authenticationToken.getName());
            if (authorizedClient != null) {
                String accessToken = authorizedClient.getAccessToken().getTokenValue();
                SpotifyUser spotifyUser = getUserProfileFromSpotify(accessToken);
                String redirectUrl = "http://localhost:8080/users/register?userName="
                        + spotifyUser.getDisplayName()
                        + "&email=" + spotifyUser.getEmail();
                redirectStrategy.sendRedirect(request, response, redirectUrl);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading authorized client");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private SpotifyUser getUserProfileFromSpotify(String accesToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accesToken);
        headers.add("Authorization", "Bearer " + accesToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RequestEntity<Void> rEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create("https://api.spotify.com/v1/me"));
        ResponseEntity<SpotifyUser> response = restTemplate.exchange(spotifyUserInfoUri,
                HttpMethod.GET,
                entity,
                SpotifyUser.class);
        return response.getBody();
    }
}
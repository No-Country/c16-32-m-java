package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Entities.SpotifyUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class SpotifyController {
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.provider.spotify.user-info-uri}")
    private String spotifyUserInfoUri;
    @Value("${spring.security.oauth2.client.provider.spotify.token-uri}")
    private String tokenUrl;
    @Value("${spring.security.oauth2.client.registration.spotify.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.spotify.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.spotify.redirect-uri}")
    private String redirectUri;
    @Value("${spring.security.oauth2.client.registration.spotify.scope}")
    private String scope;

    public SpotifyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/v1/chatBeat/spotify")
    public ResponseEntity<String> handleSpotifyAuth(@AuthenticationPrincipal
                                                        OAuth2AuthenticationToken authenticationToken,
                                                    @RequestParam(name = "code") String code) {
        String accessToken = exchangeCodeForAccessToken(code);

        String userInfo = getUserProfileFromSpotify(accessToken);

        String username = getUsernameFromUserInfo(userInfo);

        return ResponseEntity.ok("Successfully authenticated: " + username);
    }

    private String getUserProfileFromSpotify(String accesToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accesToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                .exchange(spotifyUserInfoUri,
                        HttpMethod.GET,
                        entity,
                        String.class);
        return response.getBody();
    }

    private String exchangeCodeForAccessToken(String code) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUrl)
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", code)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret);
        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), null, String.class);
        return response.getBody();
    }

    private String getUsernameFromUserInfo(String userInfo) {
        JSONObject json = new JSONObject(userInfo);
        return json.getString("display_name");
    }

    /*@GetMapping("/userInfo")
    @PreAuthorize("hasAuthority('SCOPE_user-read-email')")
    public ResponseEntity<SpotifyUser> getUserInfo(@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {

        if (authenticationToken != null) {
            OAuth2AuthorizedClient authorizedClient = authorizedClientService
                    .loadAuthorizedClient("spotify",
                            authenticationToken.getName());
            if (authorizedClient != null) {
                String accessToken = authorizedClient.getAccessToken().getTokenValue();
                SpotifyUser spotifyUser = getUserProfileFromSpotify(accessToken);
                return ResponseEntity.ok(spotifyUser);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }*/
}
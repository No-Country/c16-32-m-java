/*package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class GoogleController {
    @GetMapping
    /*obtener token del usuario*/
    public Map<String, Object> currentUser(OAuth2AuthenticationToken OAuth2AuthenticationToken) {
        return OAuth2AuthenticationToken.getPrincipal().getAttributes();
    }

    /*cambiar el URI de redireccionamiento autorizados asi redirecciona a nuestra pagina creada*/

}
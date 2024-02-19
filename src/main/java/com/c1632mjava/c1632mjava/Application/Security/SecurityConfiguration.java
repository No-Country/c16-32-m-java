package com.c1632mjava.c1632mjava.Application.Security;

import com.c1632mjava.c1632mjava.Application.Filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity (securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest.requestMatchers("/oauth/spotify").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(ologin ->
                        ologin.authorizationEndpoint(aEndpoint ->
                                aEndpoint.baseUri("/oauth/spotify"))
                                .defaultSuccessUrl("/users/register"))
                .cors(Customizer.withDefaults())
                .headers(headers -> headers.xssProtection(Customizer.withDefaults()))
                .build();
    }
}
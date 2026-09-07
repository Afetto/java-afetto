package br.com.fiap.java_afetto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/usuario")
                        .permitAll()

                        .requestMatchers(HttpMethod.PATCH, "/usuario/*/role")
                        .authenticated()

                        .requestMatchers("/pais").hasRole("ADMIN")
                        .requestMatchers("/estado").hasRole("ADMIN")
                        .requestMatchers("/cidade").hasRole("ADMIN")
                        .requestMatchers("/bairro").hasRole("ADMIN")
                        .requestMatchers("/logradouro").hasRole("ADMIN")
                        .requestMatchers("/endereco").authenticated()

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(AbstractHttpConfigurer::disable)

                .logout(LogoutConfigurer::permitAll
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
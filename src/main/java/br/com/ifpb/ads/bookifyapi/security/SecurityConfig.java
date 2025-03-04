package br.com.ifpb.ads.bookifyapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    //vou desativar o csrf e vou construir minha própria segurança e autorizações
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/livro/**", "/autor/**", "/categoria/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,"/livro/**").hasRole("USER")
                                    .requestMatchers(HttpMethod.GET,"/autor/**").hasRole("USER")
                                    .requestMatchers(HttpMethod.GET,"/categoria/**").hasRole("USER");
                            auth.anyRequest().authenticated();
                }).addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    //criptografar senhas para não deixar visivel no banco de dados
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


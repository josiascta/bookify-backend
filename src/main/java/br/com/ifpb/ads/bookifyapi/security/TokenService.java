package br.com.ifpb.ads.bookifyapi.security;

import br.com.ifpb.ads.bookifyapi.dto.LoginDTO;
import br.com.ifpb.ads.bookifyapi.entity.Role;
import br.com.ifpb.ads.bookifyapi.entity.User;
import br.com.ifpb.ads.bookifyapi.exception.RegraDeNegocioException;
import br.com.ifpb.ads.bookifyapi.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${security.token.secret}")
    private String secretKey;

    public String gerarToken(LoginDTO loginDTO) throws AuthenticationException {
        User user = userRepository.findByLogin(loginDTO.getLogin()).orElseThrow(
                    () -> new AuthenticationException("Usuário não disponível"));

        boolean eIgual = passwordEncoder.matches(loginDTO.getSenha(), user.getSenha());

        if(!eIgual) {
            throw new AuthenticationException("Senha ou login errados!");
        }

        Algorithm algorithm =  Algorithm.HMAC256(secretKey);

        List<String> roles = user.getCargos().stream()
                .map(Role::getAuthority)
                .collect(Collectors.toList());

        String token = JWT.create().withIssuer("bookify")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getIdUsuario().toString())
                .withClaim("roles", roles)
                .sign(algorithm);
        return token;
    }

}

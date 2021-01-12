package br.com.alura.forum.config.security;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ForumTokenService {

    private static final String ISSUER = "FORUM API";

    @Value("${forum.jwt.expirationInMinutes}")
    private Long expirationInMinutes;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generate(Authentication authentication) {
        final Usuario userLoged = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(userLoged.getId().toString())
                .setIssuedAt(toDate(LocalDateTime.now()))
                .setExpiration(calulateExpirationTime())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date calulateExpirationTime() {
        final var expiration = LocalDateTime.of(
                LocalDate.now(),
                LocalTime.now().plusMinutes(expirationInMinutes)
        );
        return toDate(expiration);
    }

}

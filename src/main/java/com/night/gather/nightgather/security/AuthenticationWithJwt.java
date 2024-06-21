package com.night.gather.nightgather.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.night.gather.nightgather.entity.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AuthenticationWithJwt {

    private final static Algorithm ALGORITHM = Algorithm.HMAC256("oknvoibjemrolvmdspmckvnsmvjbocmznctczxbbdc");

    public static String create(User user) {
        try {
            return JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", user.getId())
                    .withClaim("firstname", user.getFirstname())
                    .withClaim("lastname", user.getLastname())
                    .withClaim("username", user.getUsername())
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(Date.from(Instant.now().plus(7200, ChronoUnit.SECONDS)))
                    .sign(ALGORITHM);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Erreur lors de la création du token", exception);
        }
    }
}
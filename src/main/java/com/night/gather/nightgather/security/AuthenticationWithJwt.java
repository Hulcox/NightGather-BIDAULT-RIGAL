package com.night.gather.nightgather.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
            throw new JWTCreationException("🔴 Erreur lors de la création du token :", exception);
        }
    }

    public static DecodedJWT verifyJwt(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer("auth0")
                    .acceptLeeway(1)
                    .acceptExpiresAt(3600)
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("🔴 Le token est invalide : ", exception);
        }
    }
}
package com.etalent.etalent_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@ConfigurationProperties(prefix = "app")
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecretValue;

    private static String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDateValue;

    private static long jwtExpirationDate;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @PostConstruct
    public void init() {
        jwtSecret = jwtSecretValue;
        jwtExpirationDate = jwtExpirationDateValue;
        logger.info("JWT Secret: {}", jwtSecret);
        logger.info("JWT Expiration Date: {}", jwtExpirationDate);
    }

    public String generateToken(String username){
        logger.info("Generando token para el usuario: {}", username);
        try {
            Date currentDate = new Date();
            Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(expireDate)
                    .signWith(key())
                    .compact();
            logger.info("Token generada con exito: {}", token);
            return token;
        }catch (Exception e){
            logger.error("Error al generar el token: ", e);
            throw new RuntimeException("No se pudo generar el token", e);
        }
    }

    private static Key key() {
        logger.info("Generando clave para JWT. Secret length: {}", jwtSecret.length());
        if (jwtSecret == null || jwtSecret.trim().isEmpty()){
            throw new IllegalStateException("JWT secret no puede ser nulo o vacío");
        }
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
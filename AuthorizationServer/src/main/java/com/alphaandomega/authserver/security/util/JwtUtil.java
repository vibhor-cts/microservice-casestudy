package com.alphaandomega.authserver.security.util;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;

@Component
public class JwtUtil {

	private static final String SECRET = "secret";
	private static final long EXPIRATION_TIME = 10*60*1000;

	public String getToken(Authentication auth) {
		String token = JWT.create()
				.withIssuer("FIS")
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
		return token;
	}
	
}

package com.alphaandomega.books.security.utils;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

	public DecodedJWT decodeJWT(String jwt) {
		JWTVerifier verifier = getVerifier();
		verifyJwt(jwt, verifier);
		return JWT.decode(jwt);
	}

	public Claim getClaim(DecodedJWT decodedJWT, String name) {
		Claim claim = decodedJWT.getClaim(name);
		return claim;
	}

	private JWTVerifier getVerifier() {
		return JWT.require(Algorithm.HMAC512("secret".getBytes())).withIssuer("FIS").build();
	}

	private void verifyJwt(String jwt, JWTVerifier verifier) {
		try {
			verifier.verify(jwt);
		} catch (JWTDecodeException | AlgorithmMismatchException | SignatureVerificationException | TokenExpiredException | InvalidClaimException e) {
			System.out.println("Token issues");
		}
	}
}

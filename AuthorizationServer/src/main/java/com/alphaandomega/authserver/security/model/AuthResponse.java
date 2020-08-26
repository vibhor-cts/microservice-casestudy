package com.alphaandomega.authserver.security.model;

public class AuthResponse {

	private String jwt;

	public AuthResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public AuthResponse() {
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}

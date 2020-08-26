package com.alphaandomega.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alphaandomega.authserver.security.model.AuthRequest;
import com.alphaandomega.authserver.security.model.AuthResponse;
import com.alphaandomega.authserver.security.util.JwtUtil;

@RestController
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("demo")
	public String getMessage() {
		return "Demonstartion of spring security";
	}
	
	@PostMapping("authenticate") 
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception { 
		 
		Authentication authenticate = null;
		try {
			authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		String jwt = jwtUtil.getToken(authenticate);
		return new ResponseEntity<AuthResponse>(new AuthResponse(jwt), HttpStatus.OK);
	}
}

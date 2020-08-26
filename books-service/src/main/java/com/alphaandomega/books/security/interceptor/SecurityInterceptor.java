package com.alphaandomega.books.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alphaandomega.books.exception.AccessDeniedException;
import com.alphaandomega.books.security.utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.isEmpty(bearerToken)) {
			throw new AccessDeniedException("Access Denied" + ": " + "Bearer Token is Empty");
		}

		String jwt = bearerToken.replaceFirst("Bearer ", "");
		DecodedJWT decodedJWT = jwtUtils.decodeJWT(jwt);
		return true;
	}
}

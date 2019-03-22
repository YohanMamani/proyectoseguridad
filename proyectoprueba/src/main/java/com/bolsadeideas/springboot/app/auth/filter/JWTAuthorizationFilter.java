package com.bolsadeideas.springboot.app.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.Base64Utils;

import com.bolsadeideas.springboot.app.authh.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public static final String SECRET = Base64Utils.encodeToString("Alguna.Clave.Secreta.123456".getBytes());
	public static final long EXPIRATION_DATE = 14000000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(HEADER_STRING);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		if(validate(header)) {
			authentication = new UsernamePasswordAuthenticationToken(getUsername(header), null, getRoles(header));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);		
	}

	
	
	protected boolean requiresAuthentication(String header) {

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}
	
	public boolean validate(String token) {

		try {

			getClaims(token);

			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
 
	}
	
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes())
				.parseClaimsJws(resolve(token)).getBody();
		return claims;
	}
	
	public String resolve(String token) {
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			return token.replace(TOKEN_PREFIX, "");
		}
		return null;
	}
	
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
		Object roles = getClaims(token).get("authorities");

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

		return authorities;
	}
}

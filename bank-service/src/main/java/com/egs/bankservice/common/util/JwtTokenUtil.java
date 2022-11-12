package com.egs.bankservice.common.util;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.egs.bankservice.common.exception.DomainException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;


public class JwtTokenUtil {

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	public String generateAccessToken(String username) {

		return Jwts	.builder()
					.setSubject(String.format("%s", username))
					.setIssuer("tracku")
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
					.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
					.compact();

	}

	public void validateAccessToken(String token) throws DomainException {

		try{
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
		}
		catch (ExpiredJwtException ex){
			throw new DomainException("session expired!");
		}
		catch (IllegalArgumentException ex){
			throw new DomainException("invalid token!");
		}
		catch (MalformedJwtException ex){
			throw new DomainException("invalid token!");
		}
		catch (UnsupportedJwtException ex){
			throw new DomainException("invalid token!");
		}
		catch (Exception ex){
			throw new DomainException("invalid token!");
		}

	}

	public String getSubject(String token) {

		return parseClaims(token).getSubject();
	}

	private Claims parseClaims(String token) {

		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}

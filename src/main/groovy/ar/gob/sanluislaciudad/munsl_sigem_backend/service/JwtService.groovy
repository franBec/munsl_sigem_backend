package ar.gob.sanluislaciudad.munsl_sigem_backend.service

import org.springframework.security.core.userdetails.UserDetails

interface JwtService {
	String generateToken(String subject, Map<String, Object> claims)
	String extractUsername(String token)
	boolean validateToken(String token, UserDetails userDetails)
}

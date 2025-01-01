package ar.gob.sanluislaciudad.munsl_backend.service.impl

import ar.gob.sanluislaciudad.munsl_backend.config.properties.JwtConfigProperties
import ar.gob.sanluislaciudad.munsl_backend.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.util.function.Function
import javax.crypto.SecretKey
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class JwtServiceImpl implements JwtService {

	private final JwtConfigProperties jwtConfigProperties

	JwtServiceImpl(JwtConfigProperties jwtConfigProperties) {
		this.jwtConfigProperties = jwtConfigProperties
	}

	@Override
	String generateToken(String subject, Map<String, Object> claims) {
		Jwts
				.builder()
				.claims()
				.add(claims)
				.subject(subject)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * jwtConfigProperties.ttl))
				.and()
				.signWith(getKey())
				.compact()
	}

	@Override
	String extractUsername(String token) {
		extractClaim(token, { it.getSubject() })
	}

	@Override
	boolean validateToken(String token, UserDetails userDetails) {
		extractUsername(token) == userDetails.username && !isTokenExpired(token)
	}

	private SecretKey getKey() {
		Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfigProperties.key))
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		claimResolver.apply(extractAllClaims(token))
	}

	private Claims extractAllClaims(String token) {
		Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
	}

	private boolean isTokenExpired(String token) {
		extractClaim(token, { it.getExpiration() }).before(new Date())
	}
}

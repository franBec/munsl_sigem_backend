package ar.gob.sanluislaciudad.munsl_sigem_backend.config.security

import ar.gob.sanluislaciudad.munsl_sigem_backend.service.CryptoService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class HmacPasswordEncoder implements PasswordEncoder{

	private final CryptoService cryptoService

	HmacPasswordEncoder(CryptoService cryptoService) {
		this.cryptoService = cryptoService
	}

	@Override
	String encode(CharSequence rawPassword) {
		return cryptoService.hashRFC2104HMAC(rawPassword.toString().toUpperCase())
	}

	@Override
	boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword) == encodedPassword
	}
}

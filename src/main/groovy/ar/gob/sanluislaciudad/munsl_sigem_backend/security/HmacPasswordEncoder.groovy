package ar.gob.sanluislaciudad.munsl_sigem_backend.security

import ar.gob.sanluislaciudad.munsl_sigem_backend.config.properties.HmacConfigProperties
import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class HmacPasswordEncoder implements PasswordEncoder{

	private final HmacConfigProperties hmacConfigProperties
	private static final String ALGORITHM = "HmacSHA1"

	HmacPasswordEncoder(HmacConfigProperties hmacConfigProperties) {
		this.hmacConfigProperties = hmacConfigProperties
	}

	@Override
	String encode(CharSequence rawPassword) {
		return hashRFC2104HMAC(rawPassword.toString().toUpperCase())
	}

	@Override
	boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword) == encodedPassword
	}

	private String hashRFC2104HMAC(String s) {
		Mac mac = Mac.getInstance(ALGORITHM)
		mac.init(new SecretKeySpec(hmacConfigProperties.salt.getBytes(StandardCharsets.UTF_8), ALGORITHM))
		Base64.getEncoder().encodeToString(mac.doFinal(s.getBytes(StandardCharsets.UTF_8)))
	}
}

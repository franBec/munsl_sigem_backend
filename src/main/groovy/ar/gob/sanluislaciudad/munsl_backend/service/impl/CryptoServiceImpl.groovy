package ar.gob.sanluislaciudad.munsl_backend.service.impl

import ar.gob.sanluislaciudad.munsl_backend.config.properties.HmacConfigProperties
import ar.gob.sanluislaciudad.munsl_backend.service.CryptoService
import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.springframework.stereotype.Service

@Service
class CryptoServiceImpl implements CryptoService {

	private static final String ALGORITHM = "HmacSHA1"
	private final HmacConfigProperties hmacProperties

	CryptoServiceImpl(HmacConfigProperties hmacProperties) {
		this.hmacProperties = hmacProperties
	}

	@Override
	String hashRFC2104HMAC(String s) {
		Mac mac = Mac.getInstance(ALGORITHM)
		mac.init(new SecretKeySpec(hmacProperties.salt.getBytes(StandardCharsets.UTF_8), ALGORITHM))
		Base64.getEncoder().encodeToString(mac.doFinal(s.getBytes(StandardCharsets.UTF_8)))
	}
}

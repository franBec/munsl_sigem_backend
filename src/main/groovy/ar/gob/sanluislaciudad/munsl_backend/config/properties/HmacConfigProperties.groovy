package ar.gob.sanluislaciudad.munsl_backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "hmac")
class HmacConfigProperties {
	String salt
}

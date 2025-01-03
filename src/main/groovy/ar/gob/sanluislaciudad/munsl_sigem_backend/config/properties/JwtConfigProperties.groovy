package ar.gob.sanluislaciudad.munsl_sigem_backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtConfigProperties {
	String key
	Integer ttl
}

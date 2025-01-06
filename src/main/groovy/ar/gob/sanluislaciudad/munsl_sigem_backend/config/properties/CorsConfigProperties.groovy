package ar.gob.sanluislaciudad.munsl_sigem_backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cors")
class CorsConfigProperties {
	List<String> allowedOrigins
	List<String> allowedMethods
	String allowedHeaders
	Boolean allowCredentials
}

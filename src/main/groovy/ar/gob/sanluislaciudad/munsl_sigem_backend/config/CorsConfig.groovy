package ar.gob.sanluislaciudad.munsl_sigem_backend.config

import ar.gob.sanluislaciudad.munsl_sigem_backend.config.properties.CorsConfigProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig implements WebMvcConfigurer{
	private final CorsConfigProperties corsConfigProperties

	CorsConfig(CorsConfigProperties corsConfigProperties){
		this.corsConfigProperties = corsConfigProperties
	}

	@Override
	void addCorsMappings(CorsRegistry registry) {
		registry
				.addMapping("/**")
				.allowedOrigins(corsConfigProperties.allowedOrigins.toArray(new String[0]) as String)
				.allowedMethods(corsConfigProperties.allowedMethods.toArray(new String[0]) as String)
				.allowedHeaders(corsConfigProperties.allowedHeaders)
				.allowCredentials(corsConfigProperties.allowCredentials)
	}
}

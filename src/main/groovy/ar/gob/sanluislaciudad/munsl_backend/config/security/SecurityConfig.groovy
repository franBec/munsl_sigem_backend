package ar.gob.sanluislaciudad.munsl_backend.config.security

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console

import ar.gob.sanluislaciudad.munsl_backend.filter.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

	private final UserDetailsService userDetailsService
	private final HmacPasswordEncoder hmacPasswordEncoder
	private final JwtFilter jwtFilter

	SecurityConfig(UserDetailsService userDetailsService, HmacPasswordEncoder hmacPasswordEncoder, JwtFilter jwtFilter) {
		this.userDetailsService = userDetailsService
		this.hmacPasswordEncoder = hmacPasswordEncoder
		this.jwtFilter = jwtFilter
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
		httpSecurity
				.csrf {
					it
							.ignoringRequestMatchers(toH2Console())
							.disable()
				}
				.authorizeHttpRequests {
					it
							.requestMatchers(toH2Console()).permitAll()
							.requestMatchers(HttpMethod.POST, "/login").permitAll()
							.anyRequest().authenticated()
				}
				.headers{ it.frameOptions{it.disable()}}
				.httpBasic { Customizer.withDefaults() }
				.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build()
	}

	@Bean
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider()
		provider.passwordEncoder = hmacPasswordEncoder
		provider.userDetailsService = userDetailsService
		provider
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
		authenticationConfiguration.getAuthenticationManager()
	}
}

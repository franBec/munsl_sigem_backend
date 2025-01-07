package ar.gob.sanluislaciudad.munsl_sigem_backend.service.impl

import ar.gob.sanluislaciudad.munsl_sigem_backend.entity.Usuario
import ar.gob.sanluislaciudad.munsl_sigem_backend.entity.UsuarioCiudad
import ar.gob.sanluislaciudad.munsl_sigem_backend.model.LoginRequest
import ar.gob.sanluislaciudad.munsl_sigem_backend.service.JwtService
import ar.gob.sanluislaciudad.munsl_sigem_backend.service.LoginService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl implements LoginService {

	private final AuthenticationManager authenticationManager
	private final JwtService jwtService

	LoginServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager
		this.jwtService = jwtService
	}

	@Override
	Map<String, Object> login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(),
				loginRequest.getPassword()
				))
		if(!authentication.authenticated){
			throw new AuthenticationException("username $loginRequest.getUsername() authentication failed"){}
		}

		Usuario usuario = authentication.principal["usuario"] as Usuario
		UsuarioCiudad usuarioCiudad = authentication.principal["usuarioCiudad"] as UsuarioCiudad

		Map.of(
				"body", authentication.principal["authorities"].stream().map{ GrantedAuthority it -> it.authority }.toList(),
				"header", jwtService.generateToken(
				usuario ? usuario.userName : usuarioCiudad.persona.cuil as String,
				Map.of(
				"usuario_dni",usuario?.dni ?: "",
				"usuario_userName",usuario?.userName ?: "",
				"usuario_displayname",usuario?.displayName ?: "",
				"usuarioCiudad_persona_firstName",usuarioCiudad?.persona?.firstName ?: "",
				"usuarioCiudad_persona_lastName",usuarioCiudad?.persona?.lastName ?: "",
				"usuarioCiudad_persona_cuil",usuarioCiudad?.persona?.cuil ?: ""
				)
				)
				)
	}
}

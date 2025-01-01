package ar.gob.sanluislaciudad.munsl_backend.service.impl

import ar.gob.sanluislaciudad.munsl_backend.entity.Usuario
import ar.gob.sanluislaciudad.munsl_backend.entity.UsuarioCiudad
import ar.gob.sanluislaciudad.munsl_backend.model.LoginRequest
import ar.gob.sanluislaciudad.munsl_backend.service.JwtService
import ar.gob.sanluislaciudad.munsl_backend.service.LoginService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
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
	String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(),
				loginRequest.getPassword()
				))
		if(!authentication.authenticated){
			throw new AuthenticationException("username $loginRequest.getUsername() authentication failed"){}
		}

		Usuario usuario = authentication.principal["usuario"] as Usuario
		UsuarioCiudad usuarioCiudad = authentication.principal["usuarioCiudad"] as UsuarioCiudad
		jwtService.generateToken(
				usuario ? usuario.userName : usuarioCiudad.persona.cuil as String,
				Map.of(
				"usuario_id",usuario?.id ?: "",
				"usuario_dni",usuario?.dni ?: "",
				"usuario_userName",usuario?.userName ?: "",
				"usuario_displayname",usuario?.displayName ?: "",
				"usuarioCiudad_id",usuarioCiudad?.id ?: "",
				"usuarioCiudad_persona_firstName",usuarioCiudad?.persona?.firstName ?: "",
				"usuarioCiudad_persona_lastName",usuarioCiudad?.persona?.lastName ?: "",
				"usuarioCiudad_persona_cuil",usuarioCiudad?.persona?.cuil ?: ""
				)
				)
	}
}

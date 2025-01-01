package ar.gob.sanluislaciudad.munsl_backend.service.impl

import ar.gob.sanluislaciudad.munsl_backend.config.security.UserPrincipal
import ar.gob.sanluislaciudad.munsl_backend.entity.Usuario
import ar.gob.sanluislaciudad.munsl_backend.entity.UsuarioCiudad
import ar.gob.sanluislaciudad.munsl_backend.repository.UsuarioCiudadRepository
import ar.gob.sanluislaciudad.munsl_backend.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl implements UserDetailsService{

	private final UsuarioRepository usuarioRepository
	private final UsuarioCiudadRepository usuarioCiudadRepository

	UserDetailsServiceImpl(UsuarioRepository usuarioRepository, UsuarioCiudadRepository usuarioCiudadRepository) {
		this.usuarioRepository = usuarioRepository
		this.usuarioCiudadRepository = usuarioCiudadRepository
	}

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = null
		UsuarioCiudad usuarioCiudad = null

		if (username.matches("\\d+")) {
			usuarioCiudad = loadUsuarioCiudad(username as Long)
		}
		else{
			usuario = loadUsuario(username)
		}

		new UserPrincipal(usuario,usuarioCiudad)
	}

	private Usuario loadUsuario(String username){
		usuarioRepository
				.findByUserName(username)
				.orElseThrow({
					->
					throw new UsernameNotFoundException("Usuario username $username not found")
				})
	}

	private UsuarioCiudad loadUsuarioCiudad(Long cuil){
		Set<UsuarioCiudad> usuarios = usuarioCiudadRepository
				.findByPersonaCuil(cuil)

		if(usuarios.empty){
			throw new UsernameNotFoundException("UsuarioCiudad cuil $cuil not found")
		}

		if(usuarios.count {it.estado == 1} > 1){
			throw new UsernameNotFoundException("UsuarioCiudad cuil $cuil appears with field estado 1 multiple times")
		}

		usuarios.findAll {it.estado == 1}.first
	}
}

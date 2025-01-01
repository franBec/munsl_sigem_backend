package ar.gob.sanluislaciudad.munsl_backend.config.security

import ar.gob.sanluislaciudad.munsl_backend.entity.Usuario
import ar.gob.sanluislaciudad.munsl_backend.entity.UsuarioCiudad
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal implements UserDetails{

	private final Usuario usuario
	private final UsuarioCiudad usuarioCiudad

	UserPrincipal(Usuario usuario, UsuarioCiudad usuarioCiudad) {
		this.usuario = usuario
		this.usuarioCiudad = usuarioCiudad
	}

	@Override
	Collection<? extends GrantedAuthority> getAuthorities() {
		return null
	}

	@Override
	String getPassword() {
		return usuario ? usuario.password : usuarioCiudad.password
	}

	@Override
	String getUsername() {
		return usuario ? usuario.userName : usuarioCiudad.persona.cuil
	}

	@Override
	boolean isAccountNonExpired() {
		return true
	}

	@Override
	boolean isAccountNonLocked() {
		return true
	}

	@Override
	boolean isCredentialsNonExpired() {
		return true
	}

	@Override
	boolean isEnabled() {
		return usuario ? usuario.estado : usuarioCiudad.estado == 1
	}
}

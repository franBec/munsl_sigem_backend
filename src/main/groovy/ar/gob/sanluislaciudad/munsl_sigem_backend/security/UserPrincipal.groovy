package ar.gob.sanluislaciudad.munsl_sigem_backend.security

import ar.gob.sanluislaciudad.munsl_sigem_backend.entity.Usuario
import ar.gob.sanluislaciudad.munsl_sigem_backend.entity.UsuarioCiudad
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
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
		List<GrantedAuthority> authorities = []

		if (usuario) {
			authorities.addAll(usuario.roles.collectMany {
				it.permisos.collect {
					new SimpleGrantedAuthority(it.name)
				}
			})
		}

		if (usuarioCiudad) {
			authorities.addAll(usuarioCiudad.rolesCiudad.collectMany {
				it.permisosCiudad.collect {
					new SimpleGrantedAuthority(it.name)
				}
			})
		}

		return authorities
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

package ar.gob.sanluislaciudad.munsl_backend.service.impl

import ar.gob.sanluislaciudad.munsl_backend.service.PermisoService
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PermisoServiceImpl implements PermisoService {
	@Override
	List<String> getPermisos() {
		Authentication authentication = SecurityContextHolder.context.authentication

		if (Objects.isNull(authentication) || !authentication.authenticated) {
			throw new AccessDeniedException("Authentication is required to access this resource")
		}

		authentication
				.authorities
				.stream()
				.map{GrantedAuthority it -> it.authority }
				.toList()
	}
}

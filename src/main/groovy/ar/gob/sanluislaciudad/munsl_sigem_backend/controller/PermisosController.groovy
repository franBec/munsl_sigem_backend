package ar.gob.sanluislaciudad.munsl_sigem_backend.controller

import ar.gob.sanluislaciudad.munsl_sigem_backend.api.PermisosApi
import ar.gob.sanluislaciudad.munsl_sigem_backend.service.PermisoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PermisosController implements PermisosApi{

	private final PermisoService permisoService

	PermisosController(PermisoService permisoService) {
		this.permisoService = permisoService
	}

	@Override
	ResponseEntity<List<String>> getPermisosByJwt(String authorization) {
		ResponseEntity.ok(permisoService.getPermisos())
	}
}

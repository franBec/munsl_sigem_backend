package ar.gob.sanluislaciudad.munsl_backend.controller

import ar.gob.sanluislaciudad.munsl_backend.api.PermisosApi
import ar.gob.sanluislaciudad.munsl_backend.model.GetPermisosByJwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PermisosController implements PermisosApi{
    @Override
    ResponseEntity<GetPermisosByJwtResponse> getPermisosByJwt(String authorization) {
        return super.getPermisosByJwt(authorization)
    }
}

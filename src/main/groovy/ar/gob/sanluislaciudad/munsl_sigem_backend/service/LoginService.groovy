package ar.gob.sanluislaciudad.munsl_sigem_backend.service

import ar.gob.sanluislaciudad.munsl_sigem_backend.model.LoginRequest

interface LoginService {
	Map<String, Object> login(LoginRequest loginRequest)
}

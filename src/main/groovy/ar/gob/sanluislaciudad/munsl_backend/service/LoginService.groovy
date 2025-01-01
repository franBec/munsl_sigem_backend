package ar.gob.sanluislaciudad.munsl_backend.service

import ar.gob.sanluislaciudad.munsl_backend.model.LoginRequest

interface LoginService {
	String login(LoginRequest loginRequest)
}

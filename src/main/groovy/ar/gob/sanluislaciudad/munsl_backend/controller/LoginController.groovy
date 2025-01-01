package ar.gob.sanluislaciudad.munsl_backend.controller

import ar.gob.sanluislaciudad.munsl_backend.api.LoginApi
import ar.gob.sanluislaciudad.munsl_backend.model.LoginRequest
import ar.gob.sanluislaciudad.munsl_backend.service.LoginService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController implements LoginApi{

	private final LoginService loginService

	LoginController(LoginService loginService) {
		this.loginService = loginService
	}

	@Override
	ResponseEntity<Void> login(LoginRequest loginRequest) {
		HttpHeaders headers = new HttpHeaders()
		headers.add("Authorization", "Bearer " + loginService.login(loginRequest))
		new ResponseEntity<>(headers, HttpStatus.OK)
	}
}

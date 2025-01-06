package ar.gob.sanluislaciudad.munsl_sigem_backend.controller

import ar.gob.sanluislaciudad.munsl_sigem_backend.api.LoginApi
import ar.gob.sanluislaciudad.munsl_sigem_backend.model.LoginRequest
import ar.gob.sanluislaciudad.munsl_sigem_backend.service.LoginService
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
	ResponseEntity<List<String>> login(LoginRequest loginRequest) {
		Map<String, Object> loginResponseMap = loginService.login(loginRequest)

		HttpHeaders headers = new HttpHeaders()
		headers.add("Authorization", "Bearer " + loginResponseMap["header"])
		new ResponseEntity<>(loginResponseMap["body"] as List<String>,headers, HttpStatus.OK)
	}
}

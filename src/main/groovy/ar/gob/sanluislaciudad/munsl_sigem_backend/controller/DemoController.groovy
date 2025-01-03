package ar.gob.sanluislaciudad.munsl_sigem_backend.controller

import ar.gob.sanluislaciudad.munsl_sigem_backend.repository.UsuarioCiudadRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class DemoController {

	private final UsuarioCiudadRepository usuarioCiudadRepository

	DemoController(UsuarioCiudadRepository usuarioCiudadRepository) {
		this.usuarioCiudadRepository = usuarioCiudadRepository
	}

	@GetMapping
	String getHello() {
		return "hello"
	}
}

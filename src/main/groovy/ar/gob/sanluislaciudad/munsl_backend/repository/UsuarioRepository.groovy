package ar.gob.sanluislaciudad.munsl_backend.repository

import ar.gob.sanluislaciudad.munsl_backend.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByUserName(String username)
}

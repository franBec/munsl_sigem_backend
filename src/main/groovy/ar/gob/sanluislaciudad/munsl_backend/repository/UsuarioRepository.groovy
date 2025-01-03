package ar.gob.sanluislaciudad.munsl_backend.repository

import ar.gob.sanluislaciudad.munsl_backend.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	@Query("""
    SELECT u 
    FROM Usuario u 
    LEFT JOIN FETCH u.roles r 
    LEFT JOIN FETCH r.permisos 
    WHERE u.userName = :username
""")
	Optional<Usuario> findByUsernameWithRolesAndPermissions(@Param("username") String username)
}

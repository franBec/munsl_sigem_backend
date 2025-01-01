package ar.gob.sanluislaciudad.munsl_backend.repository

import ar.gob.sanluislaciudad.munsl_backend.entity.UsuarioCiudad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UsuarioCiudadRepository extends JpaRepository<UsuarioCiudad, Long>{
	@Query("SELECT u FROM UsuarioCiudad u JOIN u.persona p WHERE p.cuil = :cuil")
	Set<UsuarioCiudad> findByPersonaCuil(@Param("cuil") Long cuil)
}

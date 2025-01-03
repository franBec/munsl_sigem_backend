package ar.gob.sanluislaciudad.munsl_backend.repository

import ar.gob.sanluislaciudad.munsl_backend.entity.UsuarioCiudad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UsuarioCiudadRepository extends JpaRepository<UsuarioCiudad, Long>{
	@Query("""
    SELECT DISTINCT u
    FROM UsuarioCiudad u
    JOIN FETCH u.persona p
    LEFT JOIN FETCH u.rolesCiudad rc
    LEFT JOIN FETCH rc.permisosCiudad
    WHERE p.cuil = :cuil
""")
	Set<UsuarioCiudad> findByPersonaCuilWithRolesAndPermissions(@Param("cuil") Long cuil)
}

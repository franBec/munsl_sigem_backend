package ar.gob.sanluislaciudad.munsl_sigem_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "sgm_sec_permiso_ciudad", schema = "dbo")
class PermisoCiudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id

	@Column(name = "name", nullable = false, length = 100)
	String name

	@Column(name = "description", nullable = false, length = 250)
	String description

	@ManyToMany(mappedBy = "permisosCiudad")
	Set<RolCiudad> rolesCiudad
}

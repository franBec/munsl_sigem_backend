package ar.gob.sanluislaciudad.munsl_sigem_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "sgm_sec_rol_ciudad", schema = "dbo")
class RolCiudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id

	@Column(name = "name", nullable = false, length = 255)
	String name

	@Column(name = "description", nullable = false, length = 255)
	String description

	@ManyToMany(mappedBy = "rolesCiudad")
	Set<UsuarioCiudad> usuarioCiudad

	@ManyToMany
	@JoinTable(
	name = "sgm_sec_rol_ciudad_permisos",
	schema = "dbo",
	joinColumns = @JoinColumn(name = "rol_ciudad_id"),
	inverseJoinColumns = @JoinColumn(name = "permiso_ciudad_id")
	)
	Set<PermisoCiudad> permisosCiudad
}

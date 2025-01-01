package ar.gob.sanluislaciudad.munsl_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Immutable
@Table(name = "sgm_sec_rol", schema = "dbo")
class Rol {

	@Id
	@Column(name = "id")
	Long id

	@Column(name = "description")
	String description

	@Column(name = "name")
	String name

	@ManyToMany(mappedBy = "roles")
	Set<Usuario> usuario

	@ManyToMany
	@JoinTable(
	name = "sgm_sec_rol_permisos",
	schema = "dbo",
	joinColumns = @JoinColumn(name = "rol_id"),
	inverseJoinColumns = @JoinColumn(name = "permiso_id")
	)
	Set<Permiso> permisos
}

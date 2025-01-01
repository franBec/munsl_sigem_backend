package ar.gob.sanluislaciudad.munsl_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Immutable
@Table(name = "sgm_sec_permiso", schema = "dbo")
class Permiso {

	@Id
	@Column(name = "id")
	Long id

	@Column(name = "name")
	String name

	@Column(name = "description")
	String description

	@ManyToMany(mappedBy = "permisos")
	Set<Rol> roles
}

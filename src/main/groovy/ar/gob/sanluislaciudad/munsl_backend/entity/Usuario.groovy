package ar.gob.sanluislaciudad.munsl_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.Immutable

@Entity
@Immutable
@Table(name = "sgm_sec_usuario", schema = "dbo")
class Usuario {

	@Id
	@Column(name = "id", nullable = false)
	Long id

	@Column(name = "dni")
	String dni

	@Column(name = "user_name")
	String userName

	@Column(name = "displayname")
	String displayName

	@Column(name = "estado")
	Boolean estado

	@Column(name = "password")
	String password

	@Column(name = "created")
	Long created

	@Column(name = "modified")
	Long modified

	@Column(name = "externalguid")
	String externalGuid

	@Column(name = "fecha_cambio_clave")
	@Temporal(TemporalType.TIMESTAMP)
	Date fechaCambioClave

	@ManyToMany
	@JoinTable(
	name = "sgm_sec_usuario_roles",
	schema = "dbo",
	joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id")
	)
	Set<Rol> roles
}

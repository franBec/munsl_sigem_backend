package ar.gob.sanluislaciudad.munsl_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "sgm_sec_usuario_ciudad", schema = "dbo")
class UsuarioCiudad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	@Column(name = "created")
	Date created

	@Column(name = "modified")
	Date modified

	@Column(name = "estado")
	Integer estado

	@Column(name = "password", nullable = false, length = 255)
	String password

	@OneToOne
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	Persona persona

	@Column(name = "motivo_observacion", length = 255)
	String motivoObservacion

	@Column(name = "desicion_aceptacion", nullable = false, columnDefinition = "int default 0")
	Integer desicionAceptacion

	@Column(name = "usuario_aceptacion_id")
	Integer usuarioAceptacionId

	@Column(name = "observacion_aceptacion", length = 255)
	String observacionAceptacion

	@Column(name = "fecha_aceptacion")
	Date fechaAceptacion

	@Column(name = "consignacion_expediente_id")
	Long consignacionExpedienteId

	@Column(name = "consignacion_actuacion_id")
	Long consignacionActuacionId

	@Column(name = "estado_consignacion", nullable = false, columnDefinition = "int default 0")
	Integer estadoConsignacion

	@Column(name = "fecha_consigancion")
	Date fechaConsigancion

	@Column(name = "formulario_consignacion_id")
	Long formularioConsignacionId

	@Column(name = "usuario_creador_id")
	Integer usuarioCreadorId

	@ManyToMany
	@JoinTable(
	name = "sgm_sec_usuario_ciudad_roles",
	schema = "dbo",
	joinColumns = @JoinColumn(name = "usuario_ciudad_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_ciudad_id")
	)
	Set<RolCiudad> rolesCiudad
}

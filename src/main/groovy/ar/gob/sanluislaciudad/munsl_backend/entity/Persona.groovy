package ar.gob.sanluislaciudad.munsl_backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "sgm_persona", schema = "dbo")
class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	@Column(name = "fecha_nacimiento")
	Date fechaNacimiento

	@Column(name = "dni")
	Integer dni

	@Column(name = "modificado")
	Date modificado

	@Column(name = "first_name", length = 100)
	String firstName

	@Column(name = "ciudadano_id")
	Integer ciudadanoId

	@Column(name = "estado", nullable = false)
	Integer estado

	@Column(name = "nacionalidad", nullable = false, length = 255)
	String nacionalidad

	@Column(name = "sexo")
	Integer sexo

	@Column(name = "creado")
	Date creado

	@Column(name = "usuario_id")
	Integer usuarioId

	@Column(name = "cuil", nullable = false)
	Long cuil

	@Column(name = "telefono", nullable = false)
	Long telefono

	@Column(name = "last_name", length = 100)
	String lastName

	@Column(name = "email", nullable = false, length = 255)
	String email

	@Column(name = "direccion", nullable = false, length = 255)
	String direccion

	@Column(name = "foto_perfil_id")
	Long fotoPerfilId

	@Column(name = "cod_reset_pass", length = 255)
	String codResetPass

	@Column(name = "dni_reverso_id")
	Long dniReversoId

	@Column(name = "manzana", length = 255)
	String manzana

	@Column(name = "razon_social", length = 255)
	String razonSocial

	@Column(name = "piso", length = 255)
	String piso

	@Column(name = "inscripcion_afip_id")
	Long inscripcionAfipId

	@Column(name = "barrio_id")
	Long barrioId

	@Column(name = "monoblock", length = 255)
	String monoblock

	@Column(name = "calle", length = 255)
	String calle

	@Column(name = "calle_nro", length = 255)
	String calleNro

	@Column(name = "fecha_inicio_actividades")
	Date fechaInicioActividades

	@Column(name = "comprobante_ingresos_brutos_id")
	Long comprobanteIngresosBrutosId

	@Column(name = "casa", length = 255)
	String casa

	@Column(name = "dni_frente_id")
	Long dniFrenteId

	@Column(name = "tipo_persona", nullable = false, columnDefinition = "int default 1")
	Integer tipoPersona

	@Column(name = "cod_vincular_app", length = 255)
	String codVincularApp

	@Column(name = "fecha_cod_vilcular_app")
	Date fechaCodVilcularApp

	@Column(name = "nro_tramite", length = 255)
	String nroTramite

	@Column(name = "suscripcion_boleta_electronica")
	Boolean suscripcionBoletaElectronica

	@Column(name = "fecha_desuscripcion_boleta_electronica")
	Date fechaDesuscripcionBoletaElectronica

	@Column(name = "fecha_suscripcion_boleta_electronica")
	Date fechaSuscripcionBoletaElectronica

	@Column(name = "fecha_verificacion_perfil")
	Date fechaVerificacionPerfil

	@Column(name = "fecha_actualizacion_perfil")
	Date fechaActualizacionPerfil

	@Column(name = "es_empleado_municipal")
	Boolean esEmpleadoMunicipal

	@OneToOne(mappedBy = "persona")
	UsuarioCiudad usuarioCiudad
}

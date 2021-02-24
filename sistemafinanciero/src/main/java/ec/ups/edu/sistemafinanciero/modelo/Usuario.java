package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQueries(
		@NamedQuery(name = "Usuario.todoslosUsuarios", query = "SELECT u from Usuario u ORDER BY u.idUsuarioLong")
	)
@Table(name = "Usuario", schema = "public")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "id_usuario_seq", sequenceName = "id_usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario_seq")
	@Column(name = "id_usuario", updatable = false, unique = true, nullable = false)
	private Long idUsuarioLong;
	@Column(name = "usuario_cedula", length = 10)
	private String cedulaString;
	@Column(name = "usuario_nombre")
	private String nombre;
	@Column(name = "usuario_apellido")
	private String apellido;
	@Column(name = "usuario_email")
	private String email;
	@Column(name = "usuario_tipo")
	private String tipoString;
	@Column(name = "usuario_password")
	private String passwordString;
	@Column(name = "usuario_nombre_cuenta")
	private String nombreUsuarioString;
	@Column(name = "usuario_fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistroDate;
	
	@Column(name = "usuario_bloqueado", nullable = true)
	private boolean bloqueado;
	
	@Column(name = "usuario_int_fallidos", nullable = true)
	private int intentos;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_acceso_fk")
	private Set<Acceso> accesos;
	
	
	public Long getIdUsuarioLong() {
		return idUsuarioLong;
	}
	public void setIdUsuarioLong(Long idUsuarioLong) {
		this.idUsuarioLong = idUsuarioLong;
	}
	public String getCedulaString() {
		return cedulaString;
	}
	public void setCedulaString(String cedulaString) {
		this.cedulaString = cedulaString;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoString() {
		return tipoString;
	}
	public void setTipoString(String tipoString) {
		this.tipoString = tipoString;
	}
	public String getPasswordString() {
		return passwordString;
	}
	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}
	public String getNombreUsuarioString() {
		return nombreUsuarioString;
	}
	public void setNombreUsuarioString(String nombreUsuarioString) {
		this.nombreUsuarioString = nombreUsuarioString;
	}
	public Date getFechaRegistroDate() {
		return fechaRegistroDate;
	}
	public void setFechaRegistroDate(Date fechaRegistroDate) {
		this.fechaRegistroDate = fechaRegistroDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Acceso> getAccesos() {
		return accesos;
	}
	public void setAccesos(Set<Acceso> accesos) {
		this.accesos = accesos;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuarioLong=" + idUsuarioLong + ", cedulaString=" + cedulaString + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", email=" + email + ", tipoString=" + tipoString + ", passwordString="
				+ passwordString + ", nombreUsuarioString=" + nombreUsuarioString + ", fechaRegistroDate="
				+ fechaRegistroDate + ", bloqueado=" + bloqueado + ", intentos=" + intentos + ", accesos=" + accesos
				+ "]";
	}
}

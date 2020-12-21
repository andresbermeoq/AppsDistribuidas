package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Persona", schema = "public")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "id_persona_seq", sequenceName = "id_persona_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_persona_seq")
	@Column(name = "id_persona", updatable = false, unique = true, nullable = false)
	private int idPersonaLong;
	
	@Column(name = "persona_cedula")
	private String cedulaString;
	
	@Column(name = "persona_nombre")
	private String nombre;
	
	@Column(name = "persona_apellido")
	private String apellido;
	
	@Column(name = "persona_email")
	private String email;
	
	
	@Override
	public String toString() {
		return "Persona [idPersonaLong=" + idPersonaLong + ", cedulaString=" + cedulaString + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", email=" + email + "]";
	}

	public int getIdPersonaLong() {
		return idPersonaLong;
	}

	public void setIdPersonaLong(int idPersonaLong) {
		this.idPersonaLong = idPersonaLong;
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

	
	
	
	
}

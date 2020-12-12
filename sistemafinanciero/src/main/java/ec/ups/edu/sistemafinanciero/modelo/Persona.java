package ec.ups.edu.sistemafinanciero.modelo;

public class Persona {

	private String identificacion;
	private String tipoid;
	private String nombre;
	private String apellido;
	private String email;
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTipoid() {
		return tipoid;
	}

	public void setTipoid(String tipoid) {
		this.tipoid = tipoid;
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

	@Override
	public String toString() {
		return "Persona [identificacion=" + identificacion + ", tipoid=" + tipoid + ", nombre=" + nombre + ", apellido="
				+ apellido + ", email=" + email + "]";
	}
	
}

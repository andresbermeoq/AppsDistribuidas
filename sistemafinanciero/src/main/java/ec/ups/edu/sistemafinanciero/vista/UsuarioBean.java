package ec.ups.edu.sistemafinanciero.vista;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {

	@Inject
	private GestionUsuarioON usuarioON;

	private Usuario usuario;
	private Cajero cajero;
	private Date factual;
	private String tipo;
	
	@PostConstruct
	public void init() {
		factual = new Date();
		usuario = new Usuario();
		cajero = new Cajero();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String obtenerNombreUsuario(String nombre, String apellido) {
		return nombre+apellido;
	}
	
	public String obtenerPasswordUsuario() {
		return usuarioON.generarPassword();
	}
	public GestionUsuarioON getUsuarioON() {
		return usuarioON;
	}

	public void setUsuarioON(GestionUsuarioON usuarioON) {
		this.usuarioON = usuarioON;
	}

	public Date getFactual() {
		return factual;
	}

	public void setFactual(Date factual) {
		this.factual = factual;
	}
	
	public void doGuardar() {
		usuario.setFechaRegistroDate(factual);
		usuario.setTipoString(tipo);
		usuario.setNombreUsuarioString(this.obtenerNombreUsuario(usuario.getNombre(), usuario.getApellido()));
		usuario.setPasswordString(this.obtenerPasswordUsuario());
		//gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getPasswordString());
		usuarioON.saveUsuario(usuario);
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

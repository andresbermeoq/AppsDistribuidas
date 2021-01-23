package ec.ups.edu.sistemafinanciero.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private Usuario usuario;

	
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}
	
	
	public UsuarioBean() {
		super();
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
		return gestionUsuarioON.generarPassword();
	}
	
	public String doGuardar() {
		usuario.setNombreUsuarioString(this.obtenerNombreUsuario(usuario.getNombre(), usuario.getApellido()));
		usuario.setPasswordString(this.obtenerPasswordUsuario());
		System.out.println("USUARIO" + usuario.toString());
		gestionUsuarioON.saveUsuario(usuario);
		return null;
	}

	
	
	
	

}

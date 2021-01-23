package ec.ups.edu.sistemafinanciero.vista;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
	private GestionUsuarioON usuarioON;

	private Usuario usuario;
	private Date factual;
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}
	@Inject
	private GestionUsuarioON gestionUsuarioON;

	@PostConstruct
	public void init() {
		factual = new Date();
		usuario = new Usuario();
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
		usuario.setFechaRegistroDate(factual);
		usuario.setNombreUsuarioString(this.obtenerNombreUsuario(usuario.getNombre(), usuario.getApellido()));
		usuario.setPasswordString(this.obtenerPasswordUsuario());
		//System.out.println("USUARIO" + usuario.toString());
		//gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getPasswordString());
		gestionUsuarioON.saveUsuario(usuario);
		System.out.println(usuario);

		usuarioON.saveUsuario(usuario);		
		gestionUsuarioON.saveUsuario(usuario);
		return null;
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

	public GestionUsuarioON getGestionUsuarioON() {
		return gestionUsuarioON;
	}

	public void setGestionUsuarioON(GestionUsuarioON gestionUsuarioON) {
		this.gestionUsuarioON = gestionUsuarioON;
	}
}

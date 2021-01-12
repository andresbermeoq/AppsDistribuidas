package ec.ups.edu.sistemafinanciero.vista;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> AngelJadan
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

<<<<<<< HEAD
=======
=======
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
>>>>>>> AngelJadan
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> AngelJadan
	private GestionUsuarioON usuarioON;

	private Usuario usuario;
	private Date factual;

<<<<<<< HEAD
	private GestionUsuarioON gestionUsuarioON;
	
=======
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

=======
	private GestionUsuarioON gestionUsuarioON;
	
	private Usuario usuario;

	
	
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
>>>>>>> AngelJadan
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
<<<<<<< HEAD

	public String doGuardar() {
<<<<<<< HEAD
=======
<<<<<<< HEAD

		System.out.println(usuario);

		usuarioON.saveUsuario(usuario);
=======
		
=======
	
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
		gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getPasswordString());
>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6
		gestionUsuarioON.saveUsuario(usuario);
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
>>>>>>> AngelJadan
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

}

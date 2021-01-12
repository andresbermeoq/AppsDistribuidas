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
	private GestionUsuarioON gestionUsuarioON;
		
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

	public String doGuardar() {
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

}

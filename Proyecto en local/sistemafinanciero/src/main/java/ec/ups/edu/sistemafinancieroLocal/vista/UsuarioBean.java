package ec.ups.edu.sistemafinancieroLocal.vista;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinancieroLocal.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;
import ec.ups.edu.sistemafinancieroLocal.utils.SessionUtil;



@Named
@RequestScoped
public class UsuarioBean {

	@Inject
	private GestionUsuarioON usuarioON;

	private Usuario usuario;
	private Usuario userLogget;
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

	public void doGuardar() {
		System.out.println(usuario.toString());	
		System.out.println(SessionUtil.getIdUsuarioLogeado());
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

	public Usuario getUserLogget() {
		return userLogget;
	}

	public void setUserLogget(Usuario userLogget) {
		this.userLogget = userLogget;
	}

	public GestionUsuarioON getGestionUsuarioON() {
		return gestionUsuarioON;
	}

	public void setGestionUsuarioON(GestionUsuarioON gestionUsuarioON) {
		this.gestionUsuarioON = gestionUsuarioON;
	}
}

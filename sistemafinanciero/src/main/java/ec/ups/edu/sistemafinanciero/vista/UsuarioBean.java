package ec.ups.edu.sistemafinanciero.vista;

import java.util.ArrayList;
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


	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
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
		
		return null;
	}

}

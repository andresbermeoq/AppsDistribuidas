package ec.ups.edu.sistemafinanciero.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class SesionBean {
	
	@Inject
	private GestionUsuarioON guser;
	private Usuario user;
	
	@PostConstruct
	public void init() {
		user = new Usuario();
	}
	
	public void login() {
		Usuario newUsuario = new Usuario();
		try {
			System.out.println(user.getNombreUsuarioString()+" pass "+user.getPasswordString());
			newUsuario = guser.buscarUsuario(user.getNombreUsuarioString());
			if(newUsuario.getPasswordString()==newUsuario.getPasswordString()) {
				System.out.println("Tipo de usuario: "+newUsuario.getTipoString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}
	
	public GestionUsuarioON getGuser() {
		return guser;
	}
	public void setGuser(GestionUsuarioON guser) {
		this.guser = guser;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
}

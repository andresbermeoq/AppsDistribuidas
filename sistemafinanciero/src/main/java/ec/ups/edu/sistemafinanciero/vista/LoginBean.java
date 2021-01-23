package ec.ups.edu.sistemafinanciero.vista;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.util.MessagesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {
		
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	private Usuario user;
	
	private String usernameString;
	private String passwordString;
	
	@PostConstruct
	public void init() {
		user = new Usuario();
	}
	public String loginUser() {
		
		Usuario userUsuario = new Usuario();
		String page="";
		try {
			userUsuario = gestionUsuarioON.validarUsuarioAdmin(usernameString, passwordString);
			//System.out.println("Usuario Bean: "+userUsuario.toString());
			if (userUsuario.getTipoString().equals("Administrador")) {
				user = userUsuario;
				page= "registroPersona";
			}else if(userUsuario.getTipoString().equals("Cajero")) {
				user = userUsuario;
				page= "UsuarioView";
			}else if (userUsuario.getTipoString().equals("Cliente")) {
				user = userUsuario;
				page= "ClienteView";
			}
		} catch (GeneralException e) {
			MessagesUtil.agregarMensajeError("El Correo o la contraseña es incorrecto");
		}
		return page;
	}
	
	public String logoutUser() {
		user = new Usuario();
		//SessionUtil.getSession().invalidate();
		return "pretty:index";
	}
	

	public String getUsernameString() {
		return usernameString;
	}


	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}


	public String getPasswordString() {
		return passwordString;
	}


	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public GestionUsuarioON getGestionUsuarioON() {
		return gestionUsuarioON;
	}

	public void setGestionUsuarioON(GestionUsuarioON gestionUsuarioON) {
		this.gestionUsuarioON = gestionUsuarioON;
	}

}

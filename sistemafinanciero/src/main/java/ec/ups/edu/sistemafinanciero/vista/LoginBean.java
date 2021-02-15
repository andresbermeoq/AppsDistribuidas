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
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	@Inject 
	private LoginBean session;
	private Usuario user;
	private String usernameString;
	private String passwordString;
	
	public String loginUser() {
		
		Usuario userUsuario = new Usuario();
		String page="";
		try {
			userUsuario = gestionUsuarioON.validarUsuarioAdmin(usernameString, passwordString);
			if (userUsuario.getTipoString().equals("Administrador")) {
				page= "registroPersona";
			}
			if(userUsuario.getTipoString().equals("Cajero")) {
				page= "Cajero/CajeroView";
			}
			if (userUsuario.getTipoString().equals("Cliente")) {
				page= "ClienteView";
			}
			if (userUsuario.getTipoString().equals("Asistente de Captaciones")) {
				page = "Captaciones/indexCaptaciones";
			}
		} catch (GeneralException e) {
			MessagesUtil.agregarMensajeError("El Correo o la contraseña es incorrecto");
		}finally {
			System.out.println(user);
			user = userUsuario;
		}
		return page;
	}
	
	public String logoutUser() {
		user = new Usuario();
		return "login.xhtml";
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

	public LoginBean getSession() {
		return session;
	}

	public void setSession(LoginBean session) {
		this.session = session;
	}
	

}

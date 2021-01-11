package ec.ups.edu.sistemafinanciero.vista;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.utils.MessagesUtil;
import ec.ups.edu.sistemafinanciero.utils.SessionUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	HttpServletRequest request;

	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private String usernameString;
	private String passwordString;
	
	public String loginUser() {
		
		Usuario userUsuario;
		
		try {
			userUsuario = gestionUsuarioON.validarUsuarioAdmin(usernameString, passwordString);
			System.out.println("Usuario Bean: "+userUsuario.toString());
			if (userUsuario.getTipoString().equals("Administrador")) {
				return "registroPersona";
			}else if(userUsuario.getTipoString().equals("Cajero")) {
				return "ClienteView";
			}else if (userUsuario.getTipoString().equals("Cliente")) {
				return "UsuarioView";
			}
		} catch (GeneralException e) {
			MessagesUtil.agregarMensajeError("El Correo y Password es incorrecto");
		}
		return null;
	}
	
	public String logoutUser() {
		SessionUtil.getSession().invalidate();
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

}

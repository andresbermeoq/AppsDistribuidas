package ec.ups.edu.sistemafinanciero.vista;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.utils.MessagesUtil;
import ec.ups.edu.sistemafinanciero.utils.SessionUtil;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private String usernameString;
	private String passwordString;
	
	public String loginUser() {
		try {
			Usuario user = gestionUsuarioON.validarUsuarioAdmin(usernameString, passwordString);
			SessionUtil.setUsuarioLogeado(user);
			return "templates/AdminView";
		} catch (GeneralException e) {
			passwordString = "";
			MessagesUtil.agregarMensajeError("El Correo o Password son incorrectos");
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

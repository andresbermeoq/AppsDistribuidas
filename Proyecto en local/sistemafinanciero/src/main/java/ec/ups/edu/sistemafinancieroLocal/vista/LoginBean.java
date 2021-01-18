package ec.ups.edu.sistemafinancieroLocal.vista;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinancieroLocal.exceptions.GeneralException;
import ec.ups.edu.sistemafinancieroLocal.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;
import ec.ups.edu.sistemafinancieroLocal.utils.MessagesUtil;
import ec.ups.edu.sistemafinancieroLocal.utils.SessionUtil;
/**
 * 
 * @author Andres Bermeo
 *
 */

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

		return "";
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


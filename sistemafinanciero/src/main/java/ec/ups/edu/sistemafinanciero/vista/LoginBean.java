package ec.ups.edu.sistemafinanciero.vista;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private String usernameString;
	private String passwordString;
	
	public String loginUser() {
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

}

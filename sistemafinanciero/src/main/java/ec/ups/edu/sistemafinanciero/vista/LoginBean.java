package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Acceso;
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
	private Acceso acceso;

	public int intentosIngresoCuenta(Usuario isUsuario) {
		int intentosCuenta = gestionUsuarioON.intentosPorUsuario(isUsuario);
		return intentosCuenta;
	}

	public String loginUser() {

		Acceso newAcceso = new Acceso();
		Usuario userUsuario = new Usuario();
		String page = "";
		try {
			userUsuario = gestionUsuarioON.validarUsuarioAdmin(usernameString, passwordString);
			System.out.println(userUsuario.toString());
			if (userUsuario.getTipoString().equals("Administrador")) {
				
				if (userUsuario.getIntentos()==3) {

					FacesContext.getCurrentInstance().addMessage("formlogin", new FacesMessage("Cuenta bloqueada"));

					newAcceso.setAccessDate(new Date());
					newAcceso.setBloqueo(false);
					//newAcceso.setBloqueo(true);
					newAcceso.setContador(0);
					newAcceso.setTypeAccessString("Fallido");
					newAcceso.setUsuario(userUsuario);
					gestionUsuarioON.saveAcceso(newAcceso);
					
					userUsuario.setBloqueado(true);
					gestionUsuarioON.actualizarUsuario(userUsuario);

				} else {

					if (userUsuario.getPasswordString().equals(passwordString) && userUsuario.isBloqueado() == false) {
						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(0);
						newAcceso.setTypeAccessString("Satisfactorio");
						newAcceso.setUsuario(userUsuario);
						gestionUsuarioON.saveAcceso(newAcceso);

						page = "Admin/registroPersona";
					} else {
						FacesContext.getCurrentInstance().addMessage("formlogin",
								new FacesMessage("El usuario se encuentra bloqueado " + userUsuario.getIntentos()));

						int intentosCuenta = intentosIngresoCuenta(userUsuario) + 1;

						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(intentosCuenta);
						newAcceso.setUsuario(userUsuario);
						newAcceso.setTypeAccessString("Fallido");
						gestionUsuarioON.saveAcceso(newAcceso);
						userUsuario.setIntentos((userUsuario.getIntentos())+1);
						gestionUsuarioON.actualizarUsuario(userUsuario);
						

					}
				}
			}
			if (userUsuario.getTipoString().equals("Cajero")) {
				if (userUsuario.getIntentos() == 3) {

					FacesContext.getCurrentInstance().addMessage("formlogin", new FacesMessage("Cuenta bloqueada"));

					newAcceso.setAccessDate(new Date());
					newAcceso.setBloqueo(false);
					//newAcceso.setBloqueo(true);
					newAcceso.setContador(0);
					newAcceso.setTypeAccessString("Fallido");
					newAcceso.setUsuario(userUsuario);
					gestionUsuarioON.saveAcceso(newAcceso);
					
					userUsuario.setBloqueado(true);
					gestionUsuarioON.actualizarUsuario(userUsuario);

				} else {

					if (userUsuario.getPasswordString().equals(passwordString) && userUsuario.isBloqueado() == false) {
						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(0);
						newAcceso.setTypeAccessString("Satisfactorio");
						newAcceso.setUsuario(userUsuario);
						gestionUsuarioON.saveAcceso(newAcceso);

						page = "Cajero/CajeroView";
					} else {
						FacesContext.getCurrentInstance().addMessage("formlogin",
								new FacesMessage("El usuario se encuentra bloqueado " + userUsuario.getIntentos()));

						int intentosCuenta = intentosIngresoCuenta(userUsuario) + 1;

						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(intentosCuenta);
						newAcceso.setUsuario(userUsuario);
						newAcceso.setTypeAccessString("Satisfactorio");
						gestionUsuarioON.saveAcceso(newAcceso);
						
						userUsuario.setIntentos((userUsuario.getIntentos())+1);
						gestionUsuarioON.actualizarUsuario(userUsuario);

					}
				}
			}
			if (userUsuario.getTipoString().equals("Cliente")) {
				if (intentosIngresoCuenta(userUsuario) == 3) {

					FacesContext.getCurrentInstance().addMessage("formlogin", new FacesMessage("Cuenta bloqueada"));

					newAcceso.setAccessDate(new Date());
					newAcceso.setBloqueo(false);
					//newAcceso.setBloqueo(true);
					newAcceso.setContador(0);
					newAcceso.setTypeAccessString("Fallido");
					newAcceso.setUsuario(userUsuario);
					gestionUsuarioON.saveAcceso(newAcceso);
					
					userUsuario.setBloqueado(true);
					gestionUsuarioON.actualizarUsuario(userUsuario);

				} else {

					if (userUsuario.getPasswordString().equals(passwordString) && userUsuario.isBloqueado() == false) {
						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(0);
						newAcceso.setTypeAccessString("Satisfactorio");
						newAcceso.setUsuario(userUsuario);
						gestionUsuarioON.saveAcceso(newAcceso);

						page = "ClienteView";
					} else {
						FacesContext.getCurrentInstance().addMessage("formlogin",
								new FacesMessage("No entro acceso Inicio " + intentosIngresoCuenta(userUsuario)));

						int intentosCuenta = intentosIngresoCuenta(userUsuario) + 1;

						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(intentosCuenta);
						newAcceso.setUsuario(userUsuario);
						newAcceso.setTypeAccessString("Satisfactorio");
						gestionUsuarioON.saveAcceso(newAcceso);

					}
				}
			}
			if (userUsuario.getTipoString().equals("Asistente de Captaciones")) {
				if (userUsuario.getIntentos() == 3) {

					FacesContext.getCurrentInstance().addMessage("formlogin", new FacesMessage("Cuenta bloqueada"));
					newAcceso.setAccessDate(new Date());
					newAcceso.setBloqueo(true);
					newAcceso.setContador(0);
					newAcceso.setUsuario(userUsuario);
					newAcceso.setTypeAccessString("Fallido");
					gestionUsuarioON.saveAcceso(newAcceso);

				} else {

					if (userUsuario.getPasswordString().equals(passwordString) && newAcceso.isBloqueo() == false) {

						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(0);
						newAcceso.setUsuario(userUsuario);
						newAcceso.setTypeAccessString("Satisfactorio");
						gestionUsuarioON.saveAcceso(newAcceso);

						page = "Captaciones/indexCaptaciones";
					} else {
						int intentosCuenta = intentosIngresoCuenta(userUsuario) + 1;

						newAcceso.setAccessDate(new Date());
						newAcceso.setBloqueo(false);
						newAcceso.setContador(intentosCuenta);
						newAcceso.setUsuario(userUsuario);
						newAcceso.setTypeAccessString("Satisfactorio");
						gestionUsuarioON.saveAcceso(newAcceso);
						userUsuario.setIntentos((userUsuario.getIntentos())+1);
						gestionUsuarioON.actualizarUsuario(userUsuario);
					}
				}
			}
		} catch (GeneralException e) {
			MessagesUtil.agregarMensajeError("El Correo o la contraseña es incorrecto");
		} finally {
			user = userUsuario;
		}
		return page;
	}

	public String logoutUser() {
		user = new Usuario();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true";
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

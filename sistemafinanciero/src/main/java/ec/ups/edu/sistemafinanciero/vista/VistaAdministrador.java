package ec.ups.edu.sistemafinanciero.vista;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class VistaAdministrador {
	
	/*
	 *Para llamar al usuario iniciado sesion. 
	 */
	@Inject
	private LoginBean session;
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		if (session.getUser()!=null) {
			usuario = new Usuario();
		}else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void saludar() {
		/*response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("user");*/
		System.out.println(session.getUser().getNombreUsuarioString());
	}

	public LoginBean getSession() {
		return session;
	}

	public void setSession(LoginBean session) {
		this.session = session;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

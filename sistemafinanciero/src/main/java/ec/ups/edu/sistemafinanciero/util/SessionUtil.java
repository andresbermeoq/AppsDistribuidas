package ec.ups.edu.sistemafinanciero.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.ups.edu.sistemafinanciero.modelo.Usuario;

public class SessionUtil {
	
	public static String INFO_USER = "usuario";
	
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	public static void setUsuarioLogeado(Usuario usuario) {
		getSession().setAttribute(INFO_USER, usuario);
	}
	
	public static Usuario getUsuarioLogeado() {
		return (Usuario) getSession().getAttribute(INFO_USER);
	}
	
	public static long getIdUsuarioLogeado() {
		return ((Usuario) getSession().getAttribute(INFO_USER)).getIdUsuarioLong();
	}
}

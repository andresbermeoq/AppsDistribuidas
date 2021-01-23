package ec.ups.edu.sistemafinanciero.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.ups.edu.sistemafinanciero.modelo.Usuario;

public class SessionUtil {
	
	/**
	 * Nombre de la tabla de la base de datos.
	 */
	public static String INFO_USER = "usuario";
	
	/**
	 * Sirve para iniciar una seseion.
	 * @return Devuelve una sesion http
	 */
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	/**
	 * 
	 * @return Devulve una sesion temporal.
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * 
	 * @return Devulve una session temporal, para cerrar la sesion.
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	/**
	 * Para darle a la session los datos del usuario de session.
	 * @param usuario Usuario a loguear.
	 */
	public static void setUsuarioLogeado(Usuario usuario) {
		getSession().setAttribute(INFO_USER, usuario);
	}
	/**
	 * Revisar todavia.
	 * @return Para que devulva el usuario y la informacion del usuario de la session conectada.
	 */
	public static Usuario getUsuarioLogeado() {
		return (Usuario) getSession().getAttribute(INFO_USER);
	}
	/**
	 * Probando para busqueda por id.
	 * @return Para que devulva el usuario y la informacion del usuario de la session conectada.
	 */
	public static long getIdUsuarioLogeado() {
		return ((Usuario) getSession().getAttribute(INFO_USER)).getIdUsuarioLong();
	}
}

package ec.ups.edu.sistemafinancieroLocal.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesUtil {
	
	 public static void agregarMensajeDone(String mensaje) {
		FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
	 }
	 
	 public static void agregarMensaje(String mensaje) {
			FacesContext.getCurrentInstance()
						.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
	 }
	
	 public static void agregarMensajeError(String mensaje) {
		FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
	 }

}

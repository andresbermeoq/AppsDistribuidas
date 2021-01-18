package ec.ups.edu.sistemafinancieroLocal.vista;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.Bean;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinancieroLocal.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;

/**
 * 
 * @author EstAngelMesiasJadanC
 * Clase para controlar las sessiones de los usuarios.
 */
@Named
@SessionScoped// Para guardar la sesion y se pueda recuperar los datos.
public class SesionBean implements Serializable{
	
	@Inject
	private GestionUsuarioON guser;
	private Usuario user;

	
	@PostConstruct
	public void init() {
		user = new Usuario();
	}
	
	public String logout() {
		user = new Usuario();
		return "plog";
	}
	
	/**
	 * 
	 * @return Retorna el nombre del xhtml con los parametros.
	 */
	public String login() {
		Usuario newUsuario = new Usuario();
		String page="";
		try {
			newUsuario = guser.buscarUsuario(user.getNombreUsuarioString());
			
			if (newUsuario.getPasswordString().equals(user.getPasswordString())) {
				if (newUsuario.getTipoString().equals("ADMINISTRADOR")) {
					page="indexAdmin?faces-redirect=true&id="+newUsuario.getIdUsuarioLong()
					+"&user="+newUsuario.getNombreUsuarioString()+"&name="+newUsuario.getNombre()
					+"&lastname="+newUsuario.getApellido();
					user=newUsuario;
				}if (newUsuario.getTipoString().equals("CLIENTE")) {
					page="indexAdmin";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return page;
		}
		
	}
	
	public GestionUsuarioON getGuser() {
		return guser;
	}
	public void setGuser(GestionUsuarioON guser) {
		this.guser = guser;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
}

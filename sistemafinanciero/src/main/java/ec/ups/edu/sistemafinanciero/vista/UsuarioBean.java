package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Acceso;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {

	@Inject
	private GestionUsuarioON usuarioON;
	@Inject
	private LoginBean session;
	

	private Usuario usuario;
	private Cajero cajero;
	private Date factual;
	private String tipo;
	private Acceso acceso;
	private List<Acceso> accesos;
	
	@PostConstruct
	public void init() {
		if(session.getUser()!=null) {
			factual = new Date();
			usuario = new Usuario();
			cajero = new Cajero();
			acceso = new Acceso();
			accesos = new ArrayList<Acceso>();
		}else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void buscarUsuario() {
		try {
			this.usuario = usuarioON.buscarUsuario(usuario.getNombreUsuarioString());
		} catch (Exception e) {
		}
	}
	public void desbloquear() {
		try {
			usuario.setBloqueado(false);
			usuario.setIntentos(0);
			usuarioON.actualizarUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage("formdesbloqueo",
					new FacesMessage("Usuario desbloqueado"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("formdesbloqueo",
					new FacesMessage("Error "+e.getLocalizedMessage()));
		}
	}
	public void listaAccesos() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String obtenerNombreUsuario(String nombre, String apellido) {
		return nombre+apellido;
	}
	
	public String obtenerPasswordUsuario() {
		return usuarioON.generarPassword();
	}
	public GestionUsuarioON getUsuarioON() {
		return usuarioON;
	}

	public void setUsuarioON(GestionUsuarioON usuarioON) {
		this.usuarioON = usuarioON;
	}

	public Date getFactual() {
		return factual;
	}

	public void setFactual(Date factual) {
		this.factual = factual;
	}
	
	public void doGuardar() {
		usuario.setFechaRegistroDate(factual);
		usuario.setTipoString(tipo);
		usuario.setNombreUsuarioString(this.obtenerNombreUsuario(usuario.getNombre(), usuario.getApellido()));
		usuario.setPasswordString(this.obtenerPasswordUsuario());
		//gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getPasswordString());
		usuarioON.saveUsuario(usuario);
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

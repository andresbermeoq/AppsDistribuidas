package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.util.RandomUtil;

@Named
@RequestScoped
public class ClienteBean {

	
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	@Inject
	private LoginBean session;
	
	private Cliente cliente;
	private Usuario usuario;
	
	private List<Usuario> listaUsuarios;
	
	@PostConstruct
	public void init() {
		cargarListas();	
	}
	public void solicitarPoliza() {
		try {
			System.out.println(session.getUsernameString());
			System.out.println("redireccionando");
		       FacesContext.getCurrentInstance().getExternalContext()
		            .redirect("/sistemafinanciero/faces/solicitudPoliza.xhtml");
		   } catch (IOException ex) {
		       ex.printStackTrace();
		   }
	}
	
	public void cargarListas() {
		try {
			setListaUsuarios(gestionUsuarioON.obtenerUsuarios());
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String obtenerNombreCliente(String nombre, String apellido) {
		return "cli"+nombre+apellido;
	}
	
	public String obtenerPasswordCliente() {
		return RandomUtil.generarPassword();
	}
	
	public String obtenerNumeroCuenta() {
		return RandomUtil.generarNumeroCuenta();
	}
	
	public String doGuardarCliente() {
		usuario.setNombreUsuarioString(this.obtenerNombreCliente(usuario.getNombre(), usuario.getApellido()));
		usuario.setPasswordString(this.obtenerPasswordCliente());
		usuario.setTipoString("Cliente");
		System.out.println("Usuario: " + usuario.toString());
		
		cliente.setCuenta(this.obtenerNumeroCuenta());
		cliente.setUsuario(usuario);
		System.out.println("Cliente: "+cliente.toString());
		gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getPasswordString());
		gestionUsuarioON.saveUsuario(usuario);
		gestionUsuarioON.saveCliente(cliente);
		
		return null;
	}
	public LoginBean getSession() {
		return session;
	}
	public void setSession(LoginBean session) {
		this.session = session;
	}
	
}

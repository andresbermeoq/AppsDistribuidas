package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.stringtemplate.v4.compiler.CodeGenerator.region_return;

import com.ibm.icu.text.RelativeDateTimeFormatter.RelativeDateTimeUnit;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.util.RandomUtil;
import net.bytebuddy.asm.Advice.This;

@Named
@RequestScoped
public class ClienteBean {

	
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	@Inject
	private GestionTransaccionON gestionTransaccionON;	
	@Inject
	private GestionClienteON gestionClienteON;
	@Inject
	private LoginBean session;
	
	private Cliente cliente;
	private Usuario usuario;
	private Date fecha;
	private Usuario usuarioSeleccionado;
	private List<Usuario> listaUsuarios;
	private Transaccion transaccion;
	
	private String cedulaCliente;
	private Date factual;
	private List<Transaccion> estadocta;
	
	@PostConstruct
	public void init() {
		cargarListas();
		cliente = new Cliente();
		usuario = new Usuario();
		fecha = new Date();
		//cargarListas();	
		factual = new Date();
		usuario = new Usuario();
		cliente = new Cliente();
		usuarioSeleccionado = new Usuario();
		listaUsuarios = new ArrayList<Usuario>();
		estadocta = new ArrayList<Transaccion>();
		transaccion = new Transaccion();
		estadoCuenta();
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

	public void estadoCuenta() {
		Cliente cliente = new Cliente();
		try {
			System.out.println("user login"+session.getUser().getCedulaString());
			cliente = gestionClienteON.buscar(session.getUser().getCedulaString());
			estadocta = gestionTransaccionON.listarAllTransacciones(cliente.getIdClienteLong());
			for (Transaccion transaccion : estadocta) {
				System.out.println(transaccion.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	public Date getFactual() {
		return factual;
	}
	public void setFactual(Date factual) {
		this.factual = factual;
	}
	public LoginBean getSession() {
		return session;
	}
	public void setSession(LoginBean session) {
		this.session = session;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
		
	public String obtenerPasswordCliente() {
		return RandomUtil.generarPassword();
	}
	
	public String obtenerNumeroCuenta() {
		return RandomUtil.generarNumeroCuenta();
	}
	
	public void buscarUsuarioCedula() throws GeneralException {
		listaUsuarios = gestionUsuarioON.buscarUsuariosCedula(cedulaCliente);
		System.out.println(listaUsuarios.size());
		System.out.println("Bean: "+ listaUsuarios);
	}
	
	public String doGuardarCliente() {
		usuario.setPasswordString(this.obtenerPasswordCliente());
		usuario.setFechaRegistroDate(factual);
		usuario.setTipoString("Cliente");
		//gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getCedulaString());
		cliente.setCuenta(this.obtenerNumeroCuenta());
		cliente.setFechaRegistroDate(factual);
		cliente.setUsuario(usuario);
		cliente.setFechaRegistroDate(fecha);
		gestionUsuarioON.saveUsuario(usuario);
		gestionUsuarioON.saveCliente(cliente);
		
		gestionUsuarioON.saveUsuario(usuario);
		gestionUsuarioON.saveCliente(cliente);
		return "Guardado correctamente";
	}
	
	public void Seleccionado(Usuario usuarioSelect) {
		System.out.println("Usuario Seleccionado: " + usuarioSelect.toString());
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	public GestionUsuarioON getGestionUsuarioON() {
		return gestionUsuarioON;
	}
	public void setGestionUsuarioON(GestionUsuarioON gestionUsuarioON) {
		this.gestionUsuarioON = gestionUsuarioON;
	}
	public GestionTransaccionON getGestionTransaccionON() {
		return gestionTransaccionON;
	}
	public void setGestionTransaccionON(GestionTransaccionON gestionTransaccionON) {
		this.gestionTransaccionON = gestionTransaccionON;
	}
	public GestionClienteON getGestionClienteON() {
		return gestionClienteON;
	}
	public void setGestionClienteON(GestionClienteON gestionClienteON) {
		this.gestionClienteON = gestionClienteON;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Transaccion> getEstadocta() {
		return estadocta;
	}
	public void setEstadocta(List<Transaccion> estadocta) {
		this.estadocta = estadocta;
	}
	public Transaccion getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}
	
}

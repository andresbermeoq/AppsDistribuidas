package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.stringtemplate.v4.compiler.CodeGenerator.region_return;

import com.ibm.icu.text.RelativeDateTimeFormatter.RelativeDateTimeUnit;

import ec.ups.edu.sistemafinanciero.dao.IntebancarioDAO;
import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionPolizaON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Interbancario;
import ec.ups.edu.sistemafinanciero.modelo.Poliza;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;
import ec.ups.edu.sistemafinanciero.modelo.Transferencia;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.util.RandomUtil;
import net.bytebuddy.asm.Advice.This;

@Named
@RequestScoped
public class ClienteBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	@Inject
	private GestionTransaccionON gestionTransaccionON;	
	@Inject
	private GestionClienteON gestionClienteON;
	@Inject
	private GestionPolizaON gpoliza;
	@Inject
	private LoginBean session;
		
	private Cliente cliente;
	private Usuario usuario;
	private Date fecha;
	private Usuario usuarioSeleccionado;
	private List<Usuario> listaUsuarios;
	private Transaccion transaccion;
	private Transferencia transferencia;
	
	private String cedulaCliente;
	private Date factual;
	private List<Transaccion> estadocta;
	private List<Poliza>listPoliza;
	private List<Interbancario> bancos;
	private List<Cliente> clientes;
	private List<Transaccion> rangoFecha;
	
	private Date fechaInicial;
	private Date fechaFinal;
	private Interbancario interbancario;
	private double saldo;
	
	@PostConstruct
	public void init() {
		if (session.getUser()!=null) {
			fechaInicial=new Date();
			cargarListas();
			listPoliza = new ArrayList<Poliza>();
			bancos = new ArrayList<Interbancario>();
			clientes = new ArrayList<Cliente>();
			
			interbancario = new Interbancario();
			cliente = new Cliente();
			usuario = new Usuario();
			fecha = new Date();
			factual = new Date();
			usuario = new Usuario();
			cliente = new Cliente();
			usuarioSeleccionado = new Usuario();
			listaUsuarios = new ArrayList<Usuario>();
			estadocta = new ArrayList<Transaccion>();
			transaccion = new Transaccion();
			transferencia = new Transferencia();
			rangoFecha = new ArrayList<Transaccion>();
			
			estadoCuenta();
			listarPoliza();
			cargarBancos();
			saldo();
			listarClientes();
			cliente.setFechaRegistroDate(fecha);
			cliente.setCuenta(obtenerNumeroCuenta());
		}else {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void listarPoliza() {
		
		try {
			cliente=gestionClienteON.buscar(session.getUser().getCedulaString());
			listPoliza=gpoliza.listPolizaCliente(cliente.getIdClienteLong(),2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void estadoCuenta() {
		Cliente cliente = new Cliente();
		try {			
			cliente = gestionClienteON.buscar(session.getUser().getCedulaString());
			estadocta = gestionTransaccionON.listarAllTransacciones(cliente.getIdClienteLong());
			
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
	public String doGuardarCliente() {
		try {
			usuario.setPasswordString(this.obtenerPasswordCliente());
			usuario.setFechaRegistroDate(factual);
			usuario.setTipoString("Cliente");
			//gestionUsuarioON.enviarCorreoInicial(usuario, usuario.getCedulaString());
			cliente.setCuenta(this.obtenerNumeroCuenta());
			cliente.setFechaRegistroDate(factual);
			cliente.setUsuario(usuario);
			cliente.setFechaRegistroDate(factual);
			gestionUsuarioON.saveUsuario(usuario);
			gestionUsuarioON.saveCliente(cliente);
			FacesContext.getCurrentInstance().addMessage("fomrregsi",
					new FacesMessage("Cliente guardado"));
			usuario = new Usuario();
			cliente = new Cliente();
			cliente.setCuenta(obtenerNumeroCuenta());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("fomrregsi",
					new FacesMessage("Error "+e.getLocalizedMessage()));
		}
		
		return "Guardado correctamente";
	}
	
	public void Seleccionado(Usuario usuarioSelect) {
		System.out.println("Usuario Seleccionado: " + usuarioSelect.toString());
	}
	
	public void cargarBancos() {
		try {
			bancos=gestionTransaccionON.listarInterbancario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo para guardar una cuenta externa.
	 */
	public void guardarBanco() {
		try {
			interbancario.setLocalizacion("EXTERNA");
			if (interbancario.getBanco().length()>5) {
				if (interbancario.getTipoCta()!=null) {
					if (interbancario.getCuenta().length()>4) {
						if (interbancario.getDniTitular().length()==10) {
							if (interbancario.getNombreTitular().length()>5) {
								if (gestionTransaccionON.registrarInterbancario(interbancario)) {
									FacesContext.getCurrentInstance().addMessage("formDatBancos",
											new FacesMessage("Datos guardados exitosamente."));
									interbancario=new Interbancario();
								}else {
									FacesContext.getCurrentInstance().addMessage("formDatBancos",
											new FacesMessage("No se ha podido registrar los datos."));
								}
							}else {
								FacesContext.getCurrentInstance().addMessage("formDatBancos",
										new FacesMessage("Ingrese el nombre valido del titular de la cuenta"));
							}
						}else {
							FacesContext.getCurrentInstance().addMessage("formDatBancos",
									new FacesMessage("Ingrese un numero de cedula igual a 10 digitos."));
						}
					}else {
						FacesContext.getCurrentInstance().addMessage("formDatBancos",
								new FacesMessage("Ingrese un numero de cuenta valido mayor a 4 caracteres."));
					}
				}else {
					FacesContext.getCurrentInstance().addMessage("formDatBancos",
							new FacesMessage("Seleccione el tipo de cuenta."));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("formDatBancos",
						new FacesMessage("Ingrese un nombre del banco valido"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 
	 * @param interbancario Cuenta externa.
	 */
	public void realizarTransferferencia() {
		System.out.println("transfiriendo "+interbancario.getId());
		//interbancario = gestionTransaccionON.buscarInterbancario(interbancario.getId());
		//System.out.println(interbancario.toString());
		Cajero cajero = new Cajero();
		//Id del cajero siempre sera 1 para transacciones en linea.
		cajero.setId(1L);
		try {
			interbancario=gestionTransaccionON.buscarInterbancario(interbancario.getId());
			if (interbancario.getId()!=null) {
				if (saldo>=transaccion.getMonto()) {
					if (transaccion.getMonto()>0) {
						cliente=gestionClienteON.buscar(session.getUser().getCedulaString());
						System.out.println("cliente: "+cliente.toString());
						if (cliente!=null) {
							transaccion.setFecha(fecha);					
							transaccion.setAgencia("EN LINEA");
							transaccion.setOperacion("TRANSFER");
							transaccion.setIdentificacion(interbancario.getDniTitular());
							transaccion.setNombre(interbancario.getNombreTitular());
							transaccion.setObservacion(transaccion.getObservacion()+" transferencia externa a "+interbancario.getBanco()+" "+interbancario.getCuenta()+" "+interbancario.getNombreTitular());					
							transaccion.setCliente(cliente);
							transaccion.setCajero(cajero);
							if (gestionTransaccionON.transaccion(transaccion)) {
								transferencia.setMonto(transaccion.getMonto());
								transferencia.setInterbancario(interbancario);
								transferencia.setTransaccion(transaccion);
								gestionTransaccionON.transferir(transferencia);
									FacesContext.getCurrentInstance().addMessage("formDatBancos",
											new FacesMessage("Transferencia realizada exitosamente."));
									transferencia= new Transferencia();
									transaccion = new Transaccion();
									saldo();
							}else {
								FacesContext.getCurrentInstance().addMessage("formDatBancos",
										new FacesMessage("No se pudo realizar la transferencia transaccion"));
							}
						}else {
							FacesContext.getCurrentInstance().addMessage("formDatBancos",
									new FacesMessage("Error cliente"));
						}
						
					}else {
						FacesContext.getCurrentInstance().addMessage("formDatBancos",
								new FacesMessage("Ingrese un monto mayor a cero."));
					}
				}else {
					FacesContext.getCurrentInstance().addMessage("formDatBancos",
							new FacesMessage("El saldo es insuficiente."));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("formDatBancos",
						new FacesMessage("Seleccione una cuenta o registre una valida."));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formDatBancos",
					new FacesMessage("Error "+e.getLocalizedMessage()));
		}
		
	}
	/**
	 * Metodo para consultar el saldo disponible.
	 */
	public void saldo() {
		try {
			cliente=gestionClienteON.buscar(session.getUser().getCedulaString());
			saldo=gestionTransaccionON.saldoActual(cliente.getIdClienteLong());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarClientes() {
		try {
			clientes=gestionClienteON.listarCliente(session.getUser().getIdUsuarioLong());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void transferenciaInterna() {
		try {
			//Beneficiario
			Cliente beneficiario = new Cliente();
			Cajero cajero = new Cajero();
			cajero.setId(1L);
			beneficiario =gestionClienteON.buscar(usuario.getCedulaString());
			
			//Cliente logueado, quien realiza la transferencia.
			cliente = gestionClienteON.buscar(session.getUser().getCedulaString());
			
			//Inicio de debito transferencia
			transaccion.setAgencia("EN LINEA");
			transaccion.setOperacion("TRANSFER");
			transaccion.setFecha(fecha);
			transaccion.setIdentificacion(beneficiario.getUsuario().getCedulaString());
			transaccion.setNombre(beneficiario.getUsuario().getNombre()+" "+beneficiario.getUsuario().getApellido());
			transaccion.setCajero(cajero);
			transaccion.setObservacion(transaccion.getObservacion()+" transferencia." );
			transaccion.setCliente(cliente);
			
			transferencia.setTransaccion(transaccion);
			transferencia.setMonto(transaccion.getMonto());
			System.out.println("Transferencia "+transaccion.toString());
			//Fin debido de transferencia
			
			if (gestionTransaccionON.transaccion(transaccion)) {
				//Inicio acreditacion beneficiario
				transaccion.setId(null);
				transaccion.setOperacion("DEPOSITO");
				transaccion.setNombre(session.getUser().getNombre()+" "+session.getUser().getApellido());
				transaccion.setIdentificacion(session.getUser().getCedulaString());
				transaccion.setObservacion("INTERBANCARIOS");
				//Aqui el beneficiario es el cliente
				transaccion.setCliente(beneficiario);
				System.out.println("Deposito "+transaccion.toString());
				
				if (gestionTransaccionON.transaccion(transaccion)) {
					FacesContext.getCurrentInstance().addMessage("fomrregsi",
							new FacesMessage("Transaccion realizada exitosamente."));
					transaccion=new Transaccion();
					transferencia = new Transferencia();
				}else {
					FacesContext.getCurrentInstance().addMessage("fomrregsi",
							new FacesMessage("No se ha realizado el deposito."));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("fomrregsi",
						new FacesMessage("No se ha realizado la transferencia."));
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void filtarEstCta() {
		try {
			cliente=gestionClienteON.buscar(session.getUser().getCedulaString());
			rangoFecha = gestionTransaccionON.listarRangoFechaEstCta(cliente.getIdClienteLong(), fechaInicial, fechaFinal);
		} catch (Exception e) {
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
	public GestionPolizaON getGpoliza() {
		return gpoliza;
	}
	public void setGpoliza(GestionPolizaON gpoliza) {
		this.gpoliza = gpoliza;
	}
	public List<Poliza> getListPoliza() {
		return listPoliza;
	}
	public void setListPoliza(List<Poliza> listPoliza) {
		this.listPoliza = listPoliza;
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public List<Interbancario> getBancos() {
		return bancos;
	}
	public void setBancos(List<Interbancario> bancos) {
		this.bancos = bancos;
	}
	public Interbancario getInterbancario() {
		return interbancario;
	}
	public void setInterbancario(Interbancario interbancario) {
		this.interbancario = interbancario;
	}
	public Transferencia getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Transaccion> getRangoFecha() {
		return rangoFecha;
	}
	public void setRangoFecha(List<Transaccion> rangoFecha) {
		this.rangoFecha = rangoFecha;
	}
	
}

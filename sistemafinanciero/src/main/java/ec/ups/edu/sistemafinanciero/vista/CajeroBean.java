package ec.ups.edu.sistemafinanciero.vista;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

@Named
@RequestScoped
public class CajeroBean implements Serializable {

	@Inject
	private GestionTransaccionON gtransaccion;
	@Inject
	private GestionClienteON gclienteon;
	@Inject
	private GestionUsuarioON gusuarioon;
	@Inject
	private LoginBean session;

	private Transaccion transaccion;
	private Cliente cliente;
	private String cedula;
	private String cuenta;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();;
		transaccion = new Transaccion();
	}
	
	/**
	 * 
	 * @param cadena Texto a evaluar si contiene numeros o letras
	 * @return 1=>entero, 2=>double, 0=>letras y caracter
	 */
	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	/**
	 * 
	 * @param cadena Texto a evaluar si contiene dato de tipo double, letras o enteros
	 * @return true, si es correcto, false, si no es double
	 */
	private static boolean isDouble(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	/**
	 * Realiza la busqueda del cliente mediente su numero de cedula y numero de cuenta bancaria.
	 */
	public void buscarCliente() {
		try {
			if (cedula.length() == 10 && isNumeric(cedula) == true) {
				if (cuenta.length() > 4) {
					try {
						this.cliente = gclienteon.buscarCliente(cedula, cuenta);
						if (this.cliente.getIdClienteLong() == null) {
							FacesContext.getCurrentInstance().addMessage("formDatBusqueda",
									new FacesMessage("Cliente no encontrado"));
						}
					} catch (Exception e) {
						FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Usuario o cuenta incorrectos", null);
						FacesContext.getCurrentInstance().addMessage("formDatBusqueda:smsDatBusqueda", javaTextMsg);

					}
				} else {
					FacesContext.getCurrentInstance().addMessage("formDatBusqueda:ctaClientebus",
							new FacesMessage("Debe ingresar un numero de cuenta del cliente"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("formDatBusqueda:cedulaClientebus",
						new FacesMessage("Debe ingresar un numero de cedula valido"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage("El numero de cedula o cuenta no pertenece a ningun cliente registrado."));
		}
	}
	/**
	 * Metodo para realizar depositos en cuentas de los clientes.
	 */
	public void depositar() {
		try {
			if (cedula.length()>0&&cuenta.length()>0) {
				if (transaccion.getNombre().length()>0&&
						transaccion.getIdentificacion().length()==10&&
						transaccion.getMonto()>0) {
					Date fechaActual = new Date();
					boolean estado = true;
					Cajero cajero = new Cajero();
					cajero = gusuarioon.buscarCajero(session.getUser().getIdUsuarioLong());
					
					transaccion.setAgencia("EN LINEA");
					transaccion.setOperacion("DEPOSITO");
					transaccion.setFecha(fechaActual);
					cliente = gclienteon.buscarCliente(cedula, cuenta);
					transaccion.setCliente(cliente);
					transaccion.setCajero(cajero);
					System.out.println("cliente: "+this.cliente.toString());
					System.out.println("Cajero datos: "+cajero.toString());
					System.out.println("Transaccion datos: "+transaccion.getMonto()+transaccion.getAgencia());
					estado = gtransaccion.transaccion(transaccion);
					if (estado==true) {
						FacesContext.getCurrentInstance().addMessage("formDatBusqueda",
								new FacesMessage("Deposito realizado exitosamente!. "));
						cliente = new Cliente();
						cedula="";
						cuenta="";
						transaccion = new Transaccion();
					}
				}else {
					FacesContext.getCurrentInstance().addMessage("formDatBusqueda",
							new FacesMessage("Por favor ingrese el nombre del depositante, cedula del depositante, \n"
									+ " y el monto que sea mayor a 0 "));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("formDatBusqueda",
						new FacesMessage("Primero ingrese el numero de cedula y numero de cuenta del \n "
								+ "cliente a depositar por favor."));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formDatBusqueda",
					new FacesMessage("Se ha generado un error al realizar el deposito. "+e.getLocalizedMessage()));
		}
		
	}

	public void retirar() {
	}
	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public GestionTransaccionON getGtransaccion() {
		return gtransaccion;
	}

	public void setGtransaccion(GestionTransaccionON gtransaccion) {
		this.gtransaccion = gtransaccion;
	}

	public GestionClienteON getGclienteon() {
		return gclienteon;
	}

	public void setGclienteon(GestionClienteON gclienteon) {
		this.gclienteon = gclienteon;
	}

	public GestionUsuarioON getGusuarioon() {
		return gusuarioon;
	}

	public void setGusuarioon(GestionUsuarioON gusuarioon) {
		this.gusuarioon = gusuarioon;
	}

	public LoginBean getSession() {
		return session;
	}

	public void setSession(LoginBean session) {
		this.session = session;
	}
}

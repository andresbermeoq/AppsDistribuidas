package ec.ups.edu.sistemafinanciero.vista;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
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
	private LoginBean session;

	private Transaccion transaccion;
	private Cliente cliente = new Cliente();;
	private String cedula;
	private String cuenta;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
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
						cliente = gclienteon.buscarCliente(cedula, cuenta);
						if (cliente.getIdClienteLong() == null) {
							System.out.println("Cli no encontrado");
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

	public void depositar() {
		transaccion.setOperacion("DEPOSITO");
		gtransaccion.transaccion(transaccion);
		System.out.println(transaccion.getOperacion());
		System.out.println(transaccion.getMonto());
		System.out.println("depositando");
		System.out.println("Cajero: "+session.getUser().toString());
		System.out.println("---");
		cliente = gclienteon.buscarCliente(cedula, cuenta);
		System.out.println("cliente: "+cliente.getUsuario().getNombre());
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

}

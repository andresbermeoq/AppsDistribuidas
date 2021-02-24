package ec.ups.edu.sistemafinanciero.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Interbancario;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;
import ec.ups.edu.sistemafinanciero.modelo.Transferencia;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Path("transacciones")
public class TransaccionServiceREST {
	
	@Inject
	private GestionClienteON clienteon;
	@Inject
	private GestionUsuarioON gestionon;
	@Inject
	private GestionTransaccionON gtransaccionon;
	
	@GET
	@Produces("application/json")
	@Path("/cajero")
	public Usuario buscarCajero(@QueryParam("user") String user) {
		Usuario cajero = new Usuario();
		cajero = gestionon.buscarUsuarioTipo(user, "CAJERO");
		return cajero;
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/cliente")
	public Cliente buscarCliente(@QueryParam("id") String idCliente, @QueryParam("cta") String cuenta) {
		Cliente cliente=new Cliente();
		cliente = clienteon.buscarCliente(idCliente, cuenta);
		return cliente;
	}
	/**
	 * Guadar los datos de una nueva cuenta para las transacciones entre cuentas internas o externas.
	 * @param interbancario Datos de la cuenta externa o interna.
	 * @return Mensaje de confirmacion o error.
	 */
	@POST
	@Path("/saveInterbancario")
	@Produces("application/json")
	@Consumes("application/json")
	public Message guardarInterbancario(Interbancario interbancario) {
		Message message = new Message();
		try {
			if(gtransaccionon.registrarInterbancario(interbancario)==true) {
				message.setCode("200");
				message.setMessage("Interbancario guardado correctamente");
			}else {
				message.setCode("500");
				message.setMessage("ERROR");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("ERROR");
		}finally {
			return message;
		}
	}
	/**
	 * Lista todas las cuentas esternas o internas.
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/interbancarios")
	public List<Interbancario> listarInter() {
		List<Interbancario> interbancarios = new ArrayList<Interbancario>();
		interbancarios = gtransaccionon.listarInterbancario();
		return interbancarios;
	}
	
	/**
	 * Realiza un filtrado de transacciones de tipo deposito de un cliente.
	 * @param id Identificacion del cliente.
	 * @return Listado de depositos.
	 */
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/list_depositos")
	public List<Transaccion> listDeposito(@QueryParam("id") long id){
		List<Transaccion> transacctions = gtransaccionon.listarDeposito(id);
		return transacctions;
	}
	
	/**
	 * 
	 * @param transaccion Transaccion a depositar
	 * @return Ok, si se registra correctamente.
	 */
	@POST
	@Path("/depositar")
	@Produces("application/json")
	@Consumes("application/json")
	public String depositar(Transaccion transaccion) {
		System.out.println(transaccion.getMonto());
		System.out.println(transaccion.getOperacion());
		gtransaccionon.transaccion(transaccion);
		return "OK";
	}
	
	@GET
	@Path("/saldo/")
	@Produces("application/json")
	@Consumes("application/json")
	public double saldo(@QueryParam("ID") long idCliente) {
		double saldo = gtransaccionon.saldoActual(idCliente);
		return saldo;
	}
	
	/**
	 * 
	 * @param transaccion Transaccion de operacion tipo retiro.
	 * @return 
	 */
	@POST
	@Path("/retirar")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean retirar(Transaccion transaccion) {
		gtransaccionon.transaccion(transaccion);
		return true;
	}
	
	@GET
	@Path("/get_transferencias")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Transferencia> getTransferencias(@QueryParam(value = "idCliente") long idCliente){
		List<Transferencia> listado = new ArrayList<Transferencia>();
		listado = gtransaccionon.listarTransferencia(idCliente);
		for (Transferencia transferencia : listado) {
			System.out.println(transferencia.getTransaccion().getId());
		}
		return listado;
	}
	/**
	 * Para realizar transferencias entre cuentas internas o externas.
	 * @param transferencia
	 * @return
	 */
	@POST
	@Path("/transferir")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean transferir(Transferencia transferencia) {
		System.out.println(transferencia.getMonto()+transferencia.getInterbancario().toString()+transferencia.getTransaccion().toString());
		boolean estado = false;
		estado = gtransaccionon.transferir(transferencia);
		return true;
	}
}

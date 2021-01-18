package ec.ups.edu.sistemafinanciero.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.mail.iap.Response;

import ec.ups.edu.sistemafinancieroLocal.gestion.GestionClienteON;
import ec.ups.edu.sistemafinancieroLocal.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinancieroLocal.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Cliente;
import ec.ups.edu.sistemafinancieroLocal.modelo.Interbancario;
import ec.ups.edu.sistemafinancieroLocal.modelo.Transaccion;
import ec.ups.edu.sistemafinancieroLocal.modelo.Transferencia;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;

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
	@Produces("application/json")
	@Path("/cliente")
	public Cliente buscarCliente(@QueryParam("id") String idCliente, @QueryParam("cta") String cuenta) {
		Cliente cliente=new Cliente();
		cliente = clienteon.buscarCliente(idCliente, cuenta);
		return cliente;
	}
	
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
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/interbancarios")
	public List<Interbancario> listarInter() {
		List<Interbancario> interbancarios = new ArrayList<Interbancario>();
		interbancarios = gtransaccionon.listarInterbancario();
		return interbancarios;
	}
	
	@POST
	@Path("/depositar")
	@Produces("application/json")
	@Consumes("application/json")
	public String depositar(Transaccion transaccion) {
		gtransaccionon.registarDeposito(transaccion);
		return "OK";
	}
	@POST
	@Path("/retirar")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean retirar(Transaccion transaccion) {
		return true;
	}
	@POST
	@Path("/transferir")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean transferir(Transferencia transferencia) {
		return true;
	}

}

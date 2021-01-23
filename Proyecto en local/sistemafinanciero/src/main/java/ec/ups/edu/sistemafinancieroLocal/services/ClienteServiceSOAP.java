package ec.ups.edu.sistemafinancieroLocal.services;

import ec.ups.edu.sistemafinancieroLocal.gestion.GestionClienteON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Cliente;


public class ClienteServiceSOAP {
	
	public ClienteServiceSOAP() {
		// TODO Auto-generated constructor stub
	}
	/*@Inject
	private GestionClienteON on;*/
	
	/*@WebMethod
	public Cliente buscarClient(@WebParam(name = "Dni") String dni, @WebParam(name = "CuentaCliente") String cuenta){
		Cliente cliente = new Cliente();
		try {
			cliente = on.buscarCliente(dni, cuenta);
			System.out.println(cliente.getUsuario());
			System.out.println("soap");
		} catch (Exception e) {
			new Exception("Se ha generado un error en la consulta"+e.getLocalizedMessage());
		}finally {
			return cliente;
		}
	}9*/
}

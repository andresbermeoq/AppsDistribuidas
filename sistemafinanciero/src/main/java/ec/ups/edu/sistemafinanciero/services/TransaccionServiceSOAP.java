package ec.ups.edu.sistemafinanciero.services;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ec.ups.edu.sistemafinancieroLocal.gestion.GestionClienteON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Cliente;

@WebService
public class TransaccionServiceSOAP {
	
	@Inject
	private GestionClienteON clienteon;
	
	@WebMethod
	public boolean depositar( @WebParam(name = "Idcliente") long idCliente,
			@WebParam(name = "cuentaCliente") String cuenta
			,@WebParam(name = "IdCajero") long idCajero, 
			@WebParam(name = "Agencia") String agencia, 
			@WebParam(name = "Monto") float monto,
			@WebParam(name = "Observacion") String observacion) {
		return true;
	}
	@WebMethod
	public boolean retiro(@WebParam(name = "Idcliente") long idCliente,
			@WebParam(name = "Cuenta") String cuenta, 
			@WebParam(name = "IdCajero") long idCajero,
			@WebParam(name = "Beneficiario") String beneficiario, 
			@WebParam(name = "DniBeneficiario") String dniBeneficiario, 
			@WebParam(name = "Monto") float monto,
			@WebParam(name = "Agencia") String agencia, 
			@WebParam(name = "Observacion") String observacion) {
		return true;
	}
	@WebMethod
	public boolean transferir(@WebParam(name = "Idcliente") long idCliente,
			@WebParam(name = "CuentaCliente") String cuentaCliente,
			@WebParam(name = "IdCajero") long idCajero, 
			@WebParam(name = "Banco") String banco,
			@WebParam(name = "CuentaBanco") String cuentaBanco,
			@WebParam(name = "TipoCuenta") String tipoCuenta, 
			@WebParam(name = "DniTitular") String dniTitular, 
			@WebParam(name = "Monto")float monto,
			@WebParam(name = "Agencia")String agencia,
			@WebParam(name = "Observacion")String observacion) {
		
		boolean estado = false;
		try {
			estado = true;
		} catch (Exception e) {
			new Exception("No se pudo realizar la transaccion"+e.getLocalizedMessage());
		}finally {
			return estado;
		}
	}
	
	@WebMethod
	public Cliente buscarClient(@WebParam(name = "Dni") String dni, @WebParam(name = "CuentaCliente") String cuenta){
		Cliente cliente = new Cliente();
		try {
			cliente = clienteon.buscarCliente(dni, cuenta);
		} catch (Exception e) {
			new Exception("Se ha generado un error en la consulta"+e.getLocalizedMessage());
		}finally {
			return cliente;
		}
	}
	
}

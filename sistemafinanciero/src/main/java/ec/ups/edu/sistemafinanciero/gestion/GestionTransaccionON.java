package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.sistemafinanciero.dao.TransaccionDAO;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

@Stateless
public class GestionTransaccionON {
	
	@Inject
	private TransaccionDAO transaccionDAO;
	

	public boolean transferencia(String numeroCuentaOrigen, String numeroCuentaDestino, double cantidad){
		
		return true;
	}
	
	public boolean registrarDeposito(String numeroCuenta,double cantidad) {
		Transaccion transaccion = new Transaccion();
		transaccion.setMonto(cantidad);
		transaccion.setSaldoActual(transaccion.getSaldoAnterior() + cantidad);
		transaccionDAO.guardarTransaccion(transaccion);
		return true;
	}
	
	public boolean registrarRetiro(String numeroCuenta,double cantidad){
		Transaccion transaccion = new Transaccion();
		transaccion.setMonto(cantidad);
		transaccion.setSaldoActual(transaccion.getSaldoAnterior() - cantidad);
		transaccionDAO.guardarTransaccion(transaccion);
		return true;
	}
	
	public List<Object> listarDeposito(){
		List<Object> list = new ArrayList<Object>();
		return list;
	}
	public List<Object> listarRetiro(){
		List<Object> list = new ArrayList<Object>();
		return list;
	}
	public boolean updateEstadoCta() {
		return true;
	}
	public boolean generarEstadoCuenta() {
		return true;
	}
	public boolean transferir() {
		return true;
	}
}

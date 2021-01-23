package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.boot.model.source.internal.hbm.AbstractEntitySourceImpl;

import ec.ups.edu.sistemafinanciero.dao.ClienteDAO;
import ec.ups.edu.sistemafinanciero.dao.IntebancarioDAO;
import ec.ups.edu.sistemafinanciero.dao.TransaccionDAO;
import ec.ups.edu.sistemafinanciero.dao.TransferenciaDAO;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Interbancario;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;
import ec.ups.edu.sistemafinanciero.modelo.Transferencia;

@Named
public class GestionTransaccionON {
	@Inject
	private TransaccionDAO transaccionDAO;
	@Inject
	private TransferenciaDAO transferenciaDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private IntebancarioDAO interDAO;
	
	/**
	 *  
	 * @param Id del cliente a buscar.
	 * @return Listado de transacciones de operancion tipo DEPOSITO.
	 */
	public List<Transaccion> listarDeposito(long clienteId){
		List<Transaccion> list = new ArrayList<Transaccion>();
		list = transaccionDAO.list("DEPOSITO", clienteId);
		return list;
	}
	/**
	 * @param Id del cliente a buscar.
	 * @return Listado de transacciones de operacion tipo RETIRO.
	 */
	public List<Transaccion> listarRetiro(long clienteId){
		List<Transaccion> list = new ArrayList<Transaccion>();
		list = transaccionDAO.list("RETIRO",clienteId);
		return list;
	}
	/**
	 * 
	 * @param idCliente Id del cliente
	 * @return List de las transacciones del id del cliente indicado.
	 */
	public List<Transaccion> listarAllTransacciones(long idCliente){
		List<Transaccion> transacciones = new ArrayList<Transaccion>();
		try {
			transacciones = transaccionDAO.list("%", idCliente);
		} catch (Exception e) {
			new Exception("Error al consultar las transacciones. "+e.getLocalizedMessage());
		}finally {
			return transacciones;
		}
	}
	
	/**
	 * 
	 * @param clienteId Id del cliente a listar las transferencias.
	 * @return List de transferencias del cliente recibido.
	 */
	public List<Transferencia> listarTransferencia(long clienteId){
		List<Transferencia> list = new ArrayList<Transferencia>();
		try {
			list = transferenciaDAO.listTransferencia(clienteId);
		} catch (Exception e) {
			new Exception("Se ha generado un error al recupera las transacciones: Error: \n"+e.getLocalizedMessage());
		}finally {
			return list;
		}
	}
	public boolean generarEstadoCuenta() {
		return true;
	}
	/**
	 * 
	 * @param transaccion Tipo de deposito, retiros.
	 * @param cliente Datos del cliente desde donde se realiza la transaccion
	 * o datos del beneficiario.
	 * @return estado true, si se realiza correctamente toda la transaccion.
	 */
	public boolean transaccion(Transaccion transaccion) {
		boolean estado = false;
		double sActual = 0;
		double nuevoSaldo = 0;
		try {
			//Consulta de saldo disponible
			sActual = saldoActual(transaccion.getCliente().getIdClienteLong());
			if (transaccion.getOperacion().equals("RETIRO")) {
				//Calculo de nuevo saldo.
				nuevoSaldo = sActual-transaccion.getMonto();
				//Asigna nuevo saldo anterior.
				transaccion.setSaldoAnterior(sActual);
				//Asigna nuevo saldo actual.
				transaccion.setSladoActual(nuevoSaldo);
				//Inserta nueva transaccion. 
				estado = transaccionDAO.insert(transaccion);
			}if (transaccion.getOperacion().equals("DEPOSITO")) {
				System.out.println("entro");
				//Realiza suma de saldo anterior mas el deposito.
				nuevoSaldo = sActual+transaccion.getMonto();
				//Asigna el saldo anterior.
				transaccion.setSaldoAnterior(sActual);
				//Asigna el nuevo saldo actual.
				transaccion.setSladoActual(nuevoSaldo);
				estado = transaccionDAO.insert(transaccion);
			}if (transaccion.getOperacion().equals("TRANSFER")) {
				nuevoSaldo = sActual-transaccion.getMonto();
				transaccion.setSaldoAnterior(sActual);
				transaccion.setSladoActual(nuevoSaldo);
				estado = transaccionDAO.insert(transaccion);
				
			}
		} catch (Exception e) {
			new Exception("Se ha generado un error al guardar la operacion "+transaccion.getOperacion()
			+" \n "+e.getLocalizedMessage());
		}finally {
			return estado;
		}
	}
	/**
	 * 
	 * @param transferencia Datos para registrar la transferencia, 
	 * comprueba si la cuenta tiene un saldo mayor o igual al monto a transferir,
	 * registra el descuento 
	 * y realiza la transferencias. 
	 * @return true, si se guarda correctamente.
	 */
	@SuppressWarnings("finally")
	public boolean transferir(Transferencia transferencia) {
		boolean estado = false;
		try {
			double saldo = saldoActual(transferencia.getTransaccion().getCliente().getIdClienteLong());
			boolean estTransaccion = false;
			boolean estTranferencia = false;
			if (saldo >=transferencia.getTransaccion().getMonto()) {
				transferencia.setMonto(transferencia.getTransaccion().getMonto());
				estTransaccion = transaccion(transferencia.getTransaccion());
				estTranferencia = transferenciaDAO.insert(transferencia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return estado;
		}
	}
	/**
	 * 
	 * @param intebancario Datos de interbancarios, para cuentas de otros bancos e internos.
	 * @return true, si se guarda correctamente. False o throw, si no se logra guardar.
	 */
	public boolean registrarInterbancario(Interbancario intebancario) {
		boolean res=false;
		try {
			res = interDAO.insert(intebancario);
		} catch (Exception e) {
			new Exception("Se ha generado un error. "+e.getLocalizedMessage());
		}finally {
			return res;
		}
	}
	/**
	 * 
	 * @return Listado de todas la cuentas internas o externas registradas.
	 */
	@SuppressWarnings("finally")
	public List<Interbancario> listarInterbancario(){
		List<Interbancario> interbancarios = new ArrayList<Interbancario>();
		try {
			interbancarios = interDAO.list();
		} catch (Exception e) {
			new Exception("Se ha generado un error al listar las cuentas. "+e.getLocalizedMessage());
		}finally {
			return interbancarios;
		}
	}
	/**
	 * 
	 * @param clienteId identificador del cliente a buscar el saldo disponible.
	 * @return Ultimo saldo disponible en la cuenta.
	 */
	@SuppressWarnings("finally")
	public double saldoActual(long clienteId) {
		Transaccion transaccion = new Transaccion();		
		try {
			transaccion = transaccionDAO.searchSalFinal(clienteId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return transaccion.getSladoActual();
		}
	}
}

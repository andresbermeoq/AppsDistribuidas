package ec.ups.edu.sistemafinancieroLocal.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinancieroLocal.modelo.Transaccion;
import ec.ups.edu.sistemafinancieroLocal.modelo.Transferencia;

@Stateless
public class TransaccionDAO {
	@Inject
	private EntityManager em;
	/**
	 * 
	 * @param transaccion
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(Transaccion transaccion) throws SQLException {
		String sql = "INSERT INTO Transacciones ("
				+ "tra_id, tra_agencia, tra_fecha, tra_identificacion, tra_monto, tra_name, tra_observacion,"
				+ " tra_operacion, tra_santerior, tra_sactual, tra_fk_cajero, tra_fk_cliente)"
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";		
		em.createNativeQuery(sql)
		.setParameter(1, transaccion.getId())
		.setParameter(2, transaccion.getAgencia())
		.setParameter(3, transaccion.getFecha())
		.setParameter(4, transaccion.getIdentificacion())
		.setParameter(5, transaccion.getMonto())
		.setParameter(6, transaccion.getNombre())
		.setParameter(7, transaccion.getObservacion())
		.setParameter(8, transaccion.getOperacion())
		.setParameter(9, transaccion.getSaldoAnterior())
		.setParameter(10, transaccion.getSladoActual())
		.setParameter(11, transaccion.getCajero().getId())
		.setParameter(12, transaccion.getCliente().getIdClienteLong())
		.executeUpdate();
		return true;
	}
	/**
	 * 
	 * @param transaccion
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Transaccion transaccion) throws SQLException {
		em.merge(transaccion);
		return true;
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Transaccion read(int id) throws SQLException{
		Transaccion transaccion = em.find(Transaccion.class, id);
		return transaccion;
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
	/**
	 * 
	 * @param operacion
	 * @param clienteId
	 * @return
	 */
	public List<Transaccion> list(String operacion, long clienteId){
		List<Transaccion> transacciones = new ArrayList<Transaccion>(); 
		String sql = "SELECT t FROM Transaccion t WHERE tra_operacion like:oper and tra_fk_cliente=:clientId";
		transacciones = em.createQuery(sql,Transaccion.class)
				.setParameter("oper", operacion)
				.setParameter("clientId", clienteId).getResultList();
		return transacciones;
	}
	/**
	 * 
	 * @param id Id de la columna tra_fk_cliente.
	 * @return Transaccion con los datos encontrados, de acuerdo al ultimo ingreso segun la fecha.
	 */
	public Transaccion searchSalFinal(long id) {
		Transaccion transaccion = new Transaccion();
		String sql = "SELECT t FROM Transacciones t"
				+ " WHERE tra_fk_cliente=:cliente and tra_fecha = (SELECT MAX(tra_fecha) FROM Transacciones t)";
		transaccion = em.createQuery(sql,Transaccion.class)
				.setParameter("cliente", id)
				.getSingleResult();
		return transaccion;
	}
	/**
	 * 
	 * @param idCliente Id del cliente para filtrar de acuerdo a esto.
	 * @return List con las transferencias del cliente.
	 * @throws SQLException
	 */
	public List<Transferencia> listTransferencia(long idCliente) throws SQLException{
		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		String sql = "SELECT t FROM Transferencia t, Transaccion r WHERE tra_id=taf_fk_transaccion "
				+ "AND tra_fk_cliente=:cliente"
				+ " ";
		transferencias = em.createNativeQuery(sql, Transferencia.class)
				.setParameter("cliente", idCliente)
				.getResultList();
		return transferencias;
	}
}
package ec.ups.edu.sistemafinanciero.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.Query;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

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
		em.persist(transaccion);
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
		String sql = "SELECT t FROM Transaccion t WHERE tra_operacion like:oper and tra_fk_cliente=:clientId"
				+ " ORDER BY tra_fecha DESC";
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
		List<Transaccion> list = new ArrayList<Transaccion>();
		String sql = "SELECT t FROM Transaccion t"
				+ " WHERE tra_fk_cliente=:cliente ORDER BY tra_fecha";
		
		list = em.createQuery(sql,Transaccion.class)
				.setParameter("cliente", id)
				.getResultList();
		for (Transaccion transaccion2 : list) {
			transaccion = transaccion2;
		}
		return transaccion;
	}
	/**
	 * 
	 * @param fechaInicial Rango inicial de fecha.
	 * @param fechaFinal Rango final de fecha.
	 * @return
	 * @throws SQLException
	 */
	public List<Transaccion> ragoFecha(long clienteId,Date fechaInicial, Date fechaFinal)throws SQLException{
		Transaccion transaccion = new Transaccion();
		List<Transaccion> list = new ArrayList<Transaccion>();
		String sql = "SELECT t FROM Transaccion t "
				+ "WHERE tra_fk_cliente=:clienteId AND tra_fecha BETWEEN :finicial AND :ffin";
		list=em.createQuery(sql,Transaccion.class)
				.setParameter("clienteId", clienteId)
				.setParameter("finicial", fechaInicial)
				.setParameter("ffin", fechaFinal)
				.getResultList();
		return list;		
	}
}
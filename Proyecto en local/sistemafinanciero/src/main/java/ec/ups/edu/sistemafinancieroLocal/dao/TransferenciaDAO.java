package ec.ups.edu.sistemafinancieroLocal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinancieroLocal.modelo.Transferencia;

@Stateless
public class TransferenciaDAO {
		
	@Inject
	private EntityManager em;
	
	public boolean insert(Transferencia transferencia) throws SQLException {
		System.out.println("Banco: "+transferencia.getInterbancario().getId());
		System.out.println("Transferencia"+transferencia.getTransaccion().getId());
		
		String sql = "INSERT INTO public.transferencias("
				+ "	taf_id, taf_monto, taf_fk_interbancario, taf_fk_transaccion)"
				+ "	VALUES (?, ?, ?, ?, ?);";		
		em.createNativeQuery(sql)
		.setParameter(1, transferencia.getId())
		.setParameter(2, transferencia.getMonto())
		.setParameter(3, transferencia.getInterbancario().getId())
		.setParameter(4, transferencia.getTransaccion().getId())
		.executeUpdate();
		return true;
	}
	public boolean update(Transferencia transferencia) throws SQLException {
		em.merge(transferencia);
		return true;
	}
	public Transferencia read(int id) throws SQLException{
		Transferencia transferencia = em.find(Transferencia.class, id);
		return transferencia;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
	/**
	 * 
	 * @param idCliente Id del cliente para filtrar de acuerdo a esto.
	 * @return List con las transferencias del cliente.
	 * @throws SQLException
	 */
	public List<Transferencia> listTransferencia(long idCliente) throws SQLException{
		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		String sql = "SELECT taf_id, taf_monto, taf_fk_interbancario, taf_fk_transaccion"
				+ " FROM Transferencias t, Transacciones r WHERE tra_id=taf_fk_transaccion "
				+ "AND tra_fk_cliente=:cliente"
				+ " ";
		transferencias = em.createNativeQuery(sql, Transferencia.class)
				.setParameter("cliente", idCliente)
				.getResultList();
		return transferencias;
	}
}
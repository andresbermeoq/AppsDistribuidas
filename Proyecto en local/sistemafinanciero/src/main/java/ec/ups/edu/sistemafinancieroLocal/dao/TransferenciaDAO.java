package ec.ups.edu.sistemafinancieroLocal.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinancieroLocal.modelo.Transferencia;

@Stateless
public class TransferenciaDAO {
		
	@Inject
	private EntityManager em;
	
	public boolean insert(Transferencia transferencia) throws SQLException {
		System.out.println("Banco: "+transferencia.getInterbancario().getBanco());
		System.out.println("Transferencia"+transferencia.getTransaccion().toString());
		
		/*
		String sql = "INSERT INTO public.transferencias("
				+ "	taf_id, taf_monto, taf_fk_interbancario, taf_fk_transaccion)"
				+ "	VALUES (?, ?, ?, ?, ?);";		
		em.createNativeQuery(sql)
		.setParameter(1, transferencia.getId())
		.setParameter(2, transferencia.getMonto())
		.setParameter(3, transferencia.getInterbancario().getId())
		.setParameter(4, transferencia.getTransaccion().getId())
		.executeUpdate();*/
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
}
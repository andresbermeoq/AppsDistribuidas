package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Transferencia;

@Stateless
public class TransferenciaDAO {
		
	@Inject
	private EntityManager em;
	
	public boolean insert(Transferencia transferencia) throws SQLException {
		em.persist(transferencia);
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
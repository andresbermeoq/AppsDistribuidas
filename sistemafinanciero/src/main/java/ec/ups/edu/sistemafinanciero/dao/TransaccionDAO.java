package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

@Stateless
public class TransaccionDAO {
	@Inject
	private EntityManager em;
	
	public boolean insert(Transaccion transaccion) throws SQLException {
		em.persist(transaccion);
		return true;
	}
	public boolean update(Transaccion transaccion) throws SQLException {
		em.merge(transaccion);
		return true;
	}
	public Transaccion read(int id) throws SQLException{
		Transaccion transaccion = em.find(Transaccion.class, id);
		return transaccion;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
}
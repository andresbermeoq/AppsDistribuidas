package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Interbancario;

@Stateless
public class IntebancarioDAO {
	@Inject
	private EntityManager em;
	
	public boolean insert(Interbancario interbancario) throws SQLException {
		em.persist(interbancario);
		return true;
	}
	public boolean update(Interbancario interbancario) throws SQLException {
		em.merge(interbancario);
		return true;
	}
	public Interbancario read(int id) throws SQLException{
		Interbancario interbancario = em.find(Interbancario.class, id);
		return interbancario;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
}
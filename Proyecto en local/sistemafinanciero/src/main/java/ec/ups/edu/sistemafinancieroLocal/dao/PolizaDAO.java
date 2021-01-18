package ec.ups.edu.sistemafinancieroLocal.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinancieroLocal.modelo.Poliza;

@Stateless
public class PolizaDAO {
	@Inject
	private EntityManager em;
	
	public boolean insert(Poliza poliza) throws SQLException {
		em.persist(poliza);
		return true;
	}
	public boolean update(Poliza poliza) throws SQLException {
		em.merge(poliza);
		return true;
	}
	public Poliza read(int id) throws SQLException{
		Poliza poliza = em.find(Poliza.class, id);
		return poliza;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
}
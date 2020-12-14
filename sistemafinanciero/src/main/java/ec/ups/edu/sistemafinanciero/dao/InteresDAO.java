package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.sistemafinanciero.modelo.Interes;

@Stateless
public class InteresDAO {
	@Inject
	private EntityManager em;
	
	public boolean insert(Interes interes) throws SQLException {
		em.persist(interes);
		return true;
	}
	public boolean update(Interes interes) throws SQLException {
		em.merge(interes);
		return true;
	}
	public Interes read(int id) throws SQLException{
		Interes interes = em.find(Interes.class, id);
		return interes;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
	public List<Interes> listInteres(){
		String jpa = "SELECT v FROM Intereses";
		Query q = em.createQuery(jpa, Interes.class);
		return (List<Interes>) q.getResultList();
	}
}
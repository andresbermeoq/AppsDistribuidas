package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public Interes readRange(long day, String tipo){
		System.out.println(day+"tipo: "+tipo);
		Interes interes = new Interes();
		try {
			String sql = "SELECT i FROM Interes i"
					+ " WHERE int_tinicial <=:day and ini_tfin >=:day2 and int_tipo=:tip";
			
			interes = (Interes) em.createQuery(sql, Interes.class)
					.setParameter("day", day).setParameter("day2", day)
					.setParameter("tip", "2").getSingleResult();			
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return interes;
		}		
	}
	public Interes read(long id)throws SQLException{
		Interes interes = new Interes();
		interes = em.find(Interes.class, id);
		return interes;
	}
	public boolean delete(int id)throws SQLException {
		em.remove(id);
		return true;		
	}
	
	public List<Interes> listInteres(){
		List<Interes> listado = new ArrayList<Interes>();
		listado = em.createQuery(
		         "Select a From Interes a", Interes.class).getResultList();
		return listado;
	}
}
package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> AngelJadan
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
<<<<<<< HEAD

import com.ibm.icu.util.BytesTrie.Result;
=======
>>>>>>> AngelJadan

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
					+ " WHERE int_tinicial<=:day and ini_tfin>=:day2 and int_tipo=:tip";
			
			interes = (Interes) em.createQuery(sql, Interes.class).setParameter("day", day).setParameter("day2", day).setParameter("tip", "2").getSingleResult();
			//query.setParameter(1, day);
			//query.setParameter(2, day);
			//System.out.println("query "+query.getSingleResult());
			
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en consulta rango dia "+day+" tipo "+tipo);
		}
		return interes;
	}
	public Interes read(long id)throws SQLException{
		Interes interes = new Interes();
		interes = em.find(Interes.class, id);
		return interes;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
<<<<<<< HEAD
	public List<Interes> listIntTipo() throws SQLException{
		List<Interes> listado = new ArrayList<Interes>();
		listado = em.createQuery(
		         "Select a From Interes a", Interes.class).getResultList();
		return listado;
=======
	public List<Interes> listInteres(){
		String jpa = "SELECT v FROM Intereses";
		Query q = em.createQuery(jpa, Interes.class);
		return (List<Interes>) q.getResultList();
>>>>>>> AngelJadan
	}
}
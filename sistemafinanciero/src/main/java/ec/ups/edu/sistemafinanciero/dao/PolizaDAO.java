package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Poliza;

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
	public List<Poliza> listPoliza(String estado){
		String sql = "SELECT FROM Polizas WHERE pol_estado =:est";
		List<Poliza> lista = new ArrayList<Poliza>();
		lista = em.createNativeQuery(sql, Poliza.class)
				.setParameter("est", estado)
				.getResultList();
		return lista;
	}
}
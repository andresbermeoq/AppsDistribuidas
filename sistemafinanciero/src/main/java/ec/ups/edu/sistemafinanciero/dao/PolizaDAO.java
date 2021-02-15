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
	/**
	 * 
	 * @param estado 1: GENERADO, 2:APROBADO, 3:RECHAZADO
	 * @return
	 */
	public List<Poliza> listPoliza(int estado){
		String sql = "SELECT p FROM Poliza p WHERE pol_estado =:est";
		List<Poliza> lista = new ArrayList<Poliza>();
		lista = em.createQuery(sql, Poliza.class)
				.setParameter("est", estado)
				.getResultList();
		return lista;
	}
	/**
	 * 
	 * @param clienteId
	 * @param estado 1: GENERADO, 2:APROBADO, 3:RECHAZADO
	 * @return
	 */
	public List<Poliza> listPolizaCliente(long clienteId, int estado){
		System.out.println(clienteId);
		String sql = "SELECT p FROM Poliza p WHERE pol_cliente_fk =:cliid and pol_estado=:est";
		List<Poliza> lista = new ArrayList<Poliza>();
		lista = em.createQuery(sql, Poliza.class)
				.setParameter("cliid", clienteId)
				.setParameter("est", estado)
				.getResultList();
		return lista;
	}
}
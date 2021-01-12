package ec.ups.edu.sistemafinanciero.dao;

<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.sql.Connection;
>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

@Stateless
public class TransaccionDAO {
<<<<<<< HEAD
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
=======
	
	@Inject
	private Connection connection;
	
	@Inject
	private EntityManager entityManager;
	
	public void guardarTransaccion(Transaccion transaccion) {
		entityManager.persist(transaccion);
	}
	
	

}
>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6

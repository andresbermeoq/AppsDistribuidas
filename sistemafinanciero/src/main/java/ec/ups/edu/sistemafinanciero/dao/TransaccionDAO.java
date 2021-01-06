package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

@Stateless
public class TransaccionDAO {
	
	@Inject
	private Connection connection;
	
	@Inject
	private EntityManager entityManager;
	
	public void guardarTransaccion(Transaccion transaccion) {
		entityManager.persist(transaccion);
	}
	
	

}

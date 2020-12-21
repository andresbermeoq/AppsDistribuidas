package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Cliente;

@Stateless
public class ClienteDAO {

	@Inject
	private Connection connection;
	
	@Inject
	private EntityManager entityManager;
	
	
	public void guardarCliente(Cliente cliente) throws SQLException {
		entityManager.persist(cliente);
	}
	
}

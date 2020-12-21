package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class UsuarioDAO {
	
	@Inject
	private Connection connection;
	
	@Inject
	private EntityManager entityManager;
	
	
	public void guardarUsuario(Usuario usuario) throws SQLException {
		entityManager.persist(usuario);
	}

}

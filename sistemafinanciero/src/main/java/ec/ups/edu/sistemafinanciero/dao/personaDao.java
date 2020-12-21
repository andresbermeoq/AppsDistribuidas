package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Persona;


@Stateless
public class personaDao {
	
	@Inject
	private Connection connection;
	
	@Inject
	private EntityManager entityManager;
	
	
	public void guardarPersona(Persona persona) throws SQLException{
		entityManager.persist(persona);	
	}
	

}

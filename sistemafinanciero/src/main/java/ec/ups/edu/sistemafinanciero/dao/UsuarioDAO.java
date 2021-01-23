package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class UsuarioDAO {
	
	@Inject
	private Connection connection;
	
	@Inject
	private EntityManager entityManager;
	
	
	public void guardarUsuario(Usuario usuario) throws SQLException {
		entityManager.persist(usuario);
		entityManager.flush();
	}
	
	public List<Usuario> obtenerUsuarios() {
		return entityManager.createNamedQuery("Usuario.todoslosUsuarios").getResultList();
	}
	
	public List<Usuario> obtenerTodosUsuarios() throws GeneralException {
		try {
			return entityManager.createQuery("from USUARIO").getResultList();
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO USUARIO: "+e.getMessage());
		}
	}
	
	public Usuario obtenerUsuario(String nombre) throws GeneralException {
		try {
			String sql= "from USUARIO usu WHERE nombreUsuarioString = :nombre ";
			
			return entityManager.createQuery(sql, Usuario.class)
						.setParameter("nombre", nombre)
						.getSingleResult();	
	
			
		} catch (NoSuchEntityException ex) {
			throw new GeneralException(101, "Usuario No Encontrado");
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO: "+e.getMessage());
		}
	}
	
	
}

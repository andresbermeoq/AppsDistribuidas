package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.antlr.v4.parse.ANTLRParser.throwsSpec_return;

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
	}
	
	public List<Usuario> obtenerTodosUsuarios() throws GeneralException {
		try {
			return entityManager.createQuery("from Usuario").getResultList();
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO USUARIO: "+e.getMessage());
		}
	}
	
	public Usuario obtenerUsuarioCorreoAdmin(String nombre) throws GeneralException {
		try {
			String sql= "SELECT * FROM usuario usu WHERE usu.usuario_nombre_cuenta=:nombre AND usu.usuario_admin=:esCliente";
			
			return entityManager
						.createQuery(sql, Usuario.class)
						.setParameter("nombre", nombre)
						.setParameter("esCliente", false)
						.getSingleResult();	
		} catch (NoSuchEntityException ex) {
			throw new GeneralException(101, "Usuario No Encontrado");
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO: "+e.getMessage());
		}
	}
	
	
}

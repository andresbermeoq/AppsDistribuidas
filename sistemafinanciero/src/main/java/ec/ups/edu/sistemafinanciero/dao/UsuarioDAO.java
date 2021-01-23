package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
	}
	/**
	 * 
	 * @param dni Identificacion, en Ecuador cedula.
	 * @return Usuario encontrado
	 */
	public Usuario read(String cedula) {
		
		Usuario user = new Usuario();
		List<Usuario> users = new ArrayList<Usuario>();
		try {
			
			String sql = "SELECT u FROM Usuario u "
					+ " WHERE usuario_cedula=:ced";
			users=entityManager.createQuery(sql, Usuario.class)
					.setParameter("ced", cedula).getResultList();
			for (Usuario usuario : users) {
				System.out.println(usuario.toString());
				if (usuario.getTipoString().equals("CLIENTE")) {
					user = usuario;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return user;
		}
	}
	/**
	 * 
	 * @param usuario Nombre de usuario de acceso
	 * @return Usuario encontrado o null, si no encuentra
	 */
	public Usuario readUsuario(String usuario) {
		Usuario user = new Usuario();
		try {
			String sql = "SELECT u FROM Usuario u" + " WHERE usuario_nombre_cuenta=:usuario";
			user = (Usuario) entityManager.createQuery(sql, Usuario.class).setParameter("usuario", usuario)
					.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la consulta del usuario");
		} finally {
			return user;
		}
	}
	public List<Usuario> obtenerTodosUsuarios() throws GeneralException {
		try {
			return entityManager.createQuery("from Usuario").getResultList();
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO USUARIO: "+e.getMessage());
		}
	}
	
	public Usuario obtenerUsuario(String nombre) throws GeneralException {
		try {
			String sql= "from Usuario usu WHERE nombreUsuarioString = :nombre ";
			
			return entityManager.createQuery(sql, Usuario.class)
						.setParameter("nombre", nombre)
						.getSingleResult();	
	
			
		} catch (NoSuchEntityException ex) {
			throw new GeneralException(101, "Usuario No Encontrado");
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO: "+e.getMessage());
		}
	}
	public Usuario readUserType(String usuario, String tipo) {
		Usuario user = new Usuario();
		List<Usuario> users = new ArrayList<Usuario>();
		String sql= "SELECT u FROM Usuario u WHERE usuario_nombre_cuenta=:usuario and usuario_tipo=:tipo";
		users=entityManager.createQuery(sql, Usuario.class)
				.setParameter("usuario", usuario)
				.setParameter("tipo", tipo).getResultList();
		for (Usuario usua : users) {
			user = usua;
		}
		return user;
	}
}

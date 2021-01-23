package ec.ups.edu.sistemafinancieroLocal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.antlr.v4.parse.ANTLRParser.throwsSpec_return;

import ec.ups.edu.sistemafinancieroLocal.exceptions.GeneralException;
import ec.ups.edu.sistemafinancieroLocal.modelo.Interes;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;

	public void guardarUsuario(Usuario usuario) throws SQLException {
		em.persist(usuario);
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
			users=em.createQuery(sql, Usuario.class)
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
			user = (Usuario) em.createQuery(sql, Usuario.class).setParameter("usuario", usuario)
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
			return em.createQuery("from Usuario").getResultList();
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO USUARIO: "+e.getMessage());
		}
	}
	public Usuario obtenerUsuario(String nombre) throws GeneralException {
		try {
			String sql= "from Usuario usu WHERE nombreUsuarioString = :nombre ";
			
			return em.createQuery(sql, Usuario.class)
						.setParameter("nombre", nombre)
						.getSingleResult();	
	
			
		} catch (NoSuchEntityException ex) {
			throw new GeneralException(101, "Usuario No Encontrado");
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO: "+e.getMessage());
		}
	}
	
	public Usuario obtenerUsuarioCorreoAdmin(String nombre) throws GeneralException {
		try {
			String sql= "SELECT usu FROM usuario usu WHERE usu.usuario_nombre_cuenta=:nombre AND usu.usuario_admin=:esCliente";
			
			return em
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
	
	
	public Usuario readUserType(String usuario, String tipo) {
		Usuario user = new Usuario();
		List<Usuario> users = new ArrayList<Usuario>();
		String sql= "SELECT u FROM Usuario u WHERE usuario_nombre_cuenta=:usuario and usuario_tipo=:tipo";
		users=em.createQuery(sql, Usuario.class)
				.setParameter("usuario", usuario)
				.setParameter("tipo", tipo).getResultList();
		for (Usuario usua : users) {
			user = usua;
		}
		return user;
	}
}

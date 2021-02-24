package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.modelo.Acceso;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class ClienteDAO {
	
	@Inject
	private EntityManager entityManager;
	
	public void guardarCliente(Cliente cliente) throws SQLException {
		entityManager.persist(cliente);
	}
	
	
	public List<Acceso> obtenerAccesoClientes(long idUsuario) throws GeneralException {
		try {
			return entityManager
					.createQuery("SELECT * FROM acceso acc WHERE acc.acceso_usuario_fk= :idUsuario", Acceso.class)
					.setParameter("idUsuario", idUsuario)
					.getResultList();
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO ACCESO: "+e.getMessage());
		}
	}
	
	/**
	 * Realiza una busqueda en base al id y al numero de cuenta del cliente, sea esta de ahorros o corriente.
	 * @param idUserfk id del Cliente
	 * @param cta Numero de cuenta del cliente
	 * @return Retorna los datos del cliente.
	 */
	public Cliente buscar(long idUserfk, String cta) throws Exception {
		Cliente cliente = new Cliente();
		System.out.println("buscar cliente dao");
		System.out.println("id user"+idUserfk+" cta "+ cta);
			try {
				String sql = "SELECT c FROM Cliente c"
						+ " WHERE cliente_usuario_fk=:id and cliente_cuenta=:cta";
				
				cliente = (Cliente) entityManager.createQuery(sql, Cliente.class)
						.setParameter("id",idUserfk).setParameter("cta", cta).getSingleResult();
			}catch (NoResultException nre) {
				nre.printStackTrace();
			}catch (NonUniqueResultException nure) {
				 nure.printStackTrace();
			}finally {
				return cliente;	
			}
	}
	public Cliente buscarClienteId(long idUsuario) throws Exception {
		String sql = "SELECT c "
				+ "FROM Cliente c "
				+ "WHERE cliente_usuario_fk=:usuario";
		Cliente cliente = new Cliente();
		cliente = (Cliente) entityManager.createQuery(sql, Cliente.class).setParameter("usuario", idUsuario).getSingleResult();
		return cliente;
	}
	public int obtenerClienteCedula(String cedulaCliente) {
		
		return entityManager.createQuery("SELECT * FROM USUARIO usu WHERE usu.usuario_cedula = :cedula")
				.setParameter("cedula", cedulaCliente)
				.getFirstResult();
	}
	
	public List<Cliente> listCliente(long userid){
		List<Cliente> users = new ArrayList<Cliente>();
		try {
			String sql = "SELECT c FROM Cliente  c WHERE cliente_usuario_fk<>:userid";
			users = entityManager.createQuery(sql, Cliente.class).setParameter("userid", userid).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return users;
		}
	}
}

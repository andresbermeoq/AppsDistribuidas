package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.modelo.Acceso;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class ClienteDAO {

	@Inject
	private Connection connection;
	
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
	public Cliente buscar(long idUserfk, String cta) {
		Cliente cliente = new Cliente();
		try {
			String sql = "SELECT c FROM Cliente c"
					+ " WHERE cliente_usuario_fk=:id and cliente_cuenta=:cta";
			
			cliente = (Cliente) entityManager.createQuery(sql, Cliente.class)
					.setParameter("id",idUserfk).setParameter("cta", Integer.parseInt(cta)).getSingleResult();
						
		} catch (Exception e) {
			new Exception("Se ha generado un error al cunsultar el cliente. "+e.getLocalizedMessage());
		}finally {
			return cliente;
		}		
	}
	
	public int obtenerClienteCedula(String cedulaCliente) {
		
		return entityManager.createQuery("SELECT * FROM USUARIO usu WHERE usu.usuario_cedula = :cedula")
				.setParameter("cedula", cedulaCliente)
				.getFirstResult();
	}
	
	
}

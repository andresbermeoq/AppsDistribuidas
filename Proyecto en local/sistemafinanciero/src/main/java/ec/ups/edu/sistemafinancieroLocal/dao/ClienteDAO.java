package ec.ups.edu.sistemafinancieroLocal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinancieroLocal.exceptions.GeneralException;
import ec.ups.edu.sistemafinancieroLocal.modelo.Acceso;
import ec.ups.edu.sistemafinancieroLocal.modelo.Cliente;
import ec.ups.edu.sistemafinancieroLocal.modelo.Interes;

@Stateless
public class ClienteDAO {
	
	@Inject
	private EntityManager em;
	
	public ClienteDAO() {
	}
	
	/**
	 * 
	 * @param cliente Recibe un objeto de tipo cliente con sus datos.
	 * @return true si se guarda sin errores y false si hay algun problema.
	 */
	public boolean guardarCliente(Cliente cliente) throws SQLException {
		try {
			em.persist(cliente);
			return true;
		} catch (Exception e) {
			new SQLException("Error al guardar los datos en dao "+e.getMessage());
			return false;
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
			
			cliente = (Cliente) em.createQuery(sql, Cliente.class)
					.setParameter("id",idUserfk).setParameter("cta", Integer.parseInt(cta)).getSingleResult();
						
		} catch (Exception e) {
			new Exception("Se ha generado un error al cunsultar el cliente. "+e.getLocalizedMessage());
		}finally {
			return cliente;
		}		
	}
	public Cliente readcta(String cuenta) {
		Cliente cliente = new Cliente();
		try {
			String sql = "SELECT c FROM cliente c"
					+ " WHERE cliente_cuenta=:cta";
			
			cliente = (Cliente) em.createQuery(sql, Cliente.class)
					.setParameter("cta",cuenta).getSingleResult();			
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return cliente;
		}		
	}
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws GeneralException
	 */
	public List<Acceso> obtenerAccesoClientes(long idUsuario) throws GeneralException {
		try {
			return em
					.createQuery("SELECT * FROM acceso acc WHERE acc.acceso_usuario_fk= :idUsuario", Acceso.class)
					.setParameter("idUsuario", idUsuario)
					.getResultList();
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO ACCESO: "+e.getMessage());
		}
	}
	
	
}

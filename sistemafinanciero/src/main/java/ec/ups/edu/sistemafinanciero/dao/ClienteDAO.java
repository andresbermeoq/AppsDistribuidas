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
	
	
}

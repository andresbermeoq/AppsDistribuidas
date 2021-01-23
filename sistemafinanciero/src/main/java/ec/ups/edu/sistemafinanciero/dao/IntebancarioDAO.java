package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Interbancario;

@Stateless
public class IntebancarioDAO {
	@Inject
	private EntityManager em;
	
	/**
	 * 
	 * @param interbancario Clase interbancario
	 * @return true, si se inserta.
	 * @throws Exception Si no se inserta y se genera un error.
	 */
	public boolean insert(Interbancario interbancario) throws Exception {
		em.createNativeQuery("INSERT INTO Interbancarios (iba_id, iba_banco, iba_cuenta, iba_dni,"
				+ " iba_titular, iba_tipocta, iba_localizacion) VALUES (?,?,?,?,?,?,?)")
		.setParameter(1, interbancario.getId())
		.setParameter(2, interbancario.getBanco())
		.setParameter(3, interbancario.getCuenta())
		.setParameter(4, interbancario.getDniTitular())
		.setParameter(5, interbancario.getNombreTitular())
		.setParameter(6, interbancario.getTipoCta())
		.setParameter(7, interbancario.getLocalizacion())
		.executeUpdate();
		return true;
	}
	public boolean update(Interbancario interbancario) throws SQLException {
		em.merge(interbancario);
		return true;
	}
	public Interbancario read(int id) throws SQLException{
		Interbancario interbancario = em.find(Interbancario.class, id);
		return interbancario;
	}
	public boolean delete(int id) throws SQLException {
		em.remove(id);
		return true;		
	}
	/**
	 * Lista todas las cuentas extertas o internas que se encuentren registradas.
	 * @return Arreglo de cuentas registradas.
	 */
	public List<Interbancario> list(){
		List<Interbancario> interbancarios = new ArrayList<Interbancario>();
		System.out.println("dao");
		String sql = "SELECT i from Interbancario i";
		interbancarios = em.createQuery(sql,Interbancario.class).getResultList();
		return interbancarios;
	}
}
package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Cajero;

@Stateless
public class CajeroDAO {
	
	@Inject
	private EntityManager em;
	
	public CajeroDAO() {
		// TODO Auto-generated constructor stub
	}
	public boolean insert(Cajero cajero)throws SQLException {
		em.persist(cajero);
		return true;
	}
	public Cajero buscar(long usuarioid) {
		Cajero cajero = new Cajero();
		try {
			String sql = "SELECT * FROM Cajero "
					+ " WHERE cajero_usu_fk=:userid";
			cajero =(Cajero)em.createNativeQuery(sql, Cajero.class).setParameter("userid", usuarioid).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return cajero;
		}
		
	}
}

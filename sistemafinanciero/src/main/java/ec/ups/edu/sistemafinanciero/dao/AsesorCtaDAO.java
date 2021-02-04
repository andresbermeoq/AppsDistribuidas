package ec.ups.edu.sistemafinanciero.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.AsesorCta;

@Stateless
public class AsesorCtaDAO {
	@Inject
	private EntityManager em;
	
	public boolean insert(AsesorCta asesor) throws SQLException {
		em.persist(asesor);
		em.flush();
		return true;
	}
}

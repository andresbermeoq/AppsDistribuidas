package ec.ups.edu.sistemafinanciero.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import ec.ups.edu.sistemafinanciero.modelo.Acceso;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class AccesoDAO {
	
	@Inject
	private EntityManager entityManager;
	
	public void guardarUsuarioAcceso(Acceso acceso) {
		entityManager.persist(acceso);
		entityManager.flush();
	}
	
	public List<Acceso> obtenerTodosLosAccesos() {
		return entityManager.createNamedQuery("Acceso.todoslosAccesos", Acceso.class).getResultList();
	}
	
//	public Acceso obtenerAccesoUsario(Usuario usuarioAcceso) {
//		String sql = "SELECT acc FROM Acceso acc WHERE acc.usuario = :usuarioAcc ORDER BY acc.idAccesoLong";
//		Query query = entityManager.createQuery(sql).setParameter("usuarioAcc", usuarioAcceso);
//		List<Acceso> accesos = query.getResultList();
//	}
	
	public Long obtenerVecesAccesoUsuario(Usuario usuarioAcceso) {
		String sql = "SELECT COUNT(bloqueo) FROM Acceso acc WHERE acc.usuario = :usuarioAcc AND acc.bloqueo = :bloqueoC";
		
		Query query = entityManager.createQuery(sql);
		query.setParameter("usuarioAcc", usuarioAcceso);
		query.setParameter("bloqueoC", false);
		
		return (Long) query.getSingleResult();
	}
	
}

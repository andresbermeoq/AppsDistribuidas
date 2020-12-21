package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;


import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.dao.personaDao;
import ec.ups.edu.sistemafinanciero.modelo.Persona;

@Named
public class GestionPersonaON{
	
	@Inject
	private personaDao personaDao;
	
	
	public void guardar(Persona persona) {
		try {
			personaDao.guardarPersona(persona);
		} catch (SQLException e) {
			System.out.println("Error DAO:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}

package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;


import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.dao.personaDao;
import ec.ups.edu.sistemafinanciero.modelo.Persona;

@Named
//<<<<<<< HEAD
//=======
public class GestionPersonaON implements GestionPersonaONRemoto {
//>>>>>>> bd3397e6baa78f2c8fc2e67463b70f5ca08fa57a
	
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

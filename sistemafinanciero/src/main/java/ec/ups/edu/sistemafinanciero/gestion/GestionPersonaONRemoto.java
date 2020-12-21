package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;

import javax.ejb.Remote;

import ec.ups.edu.sistemafinanciero.modelo.Persona;

@Remote
public interface GestionPersonaONRemoto {
	
	public void guardar(Persona persona);

}

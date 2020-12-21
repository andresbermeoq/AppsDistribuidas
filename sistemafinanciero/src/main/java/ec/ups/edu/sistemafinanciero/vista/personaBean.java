package ec.ups.edu.sistemafinanciero.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionPersonaON;
import ec.ups.edu.sistemafinanciero.modelo.Persona;

@Named
@RequestScoped
public class personaBean {

	@Inject
	private GestionPersonaON personaON;
	
	private Persona persona;
	
	public personaBean() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		persona = new Persona();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public String doGuardar() {
		
		//System.out.println(persona);
		personaON.guardar(persona);
		
		return null;
	}
	

}

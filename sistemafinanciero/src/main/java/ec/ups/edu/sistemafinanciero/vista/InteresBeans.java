package ec.ups.edu.sistemafinanciero.vista;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.modelo.Interes;

@Named
@RequestScoped
public class InteresBeans {
	private Interes newInteres;

	public Interes getNewInteres() {
		return newInteres;
	}

	public void setNewInteres(Interes newInteres) {
		this.newInteres = newInteres;
	}
	public String guardarInteres() {
		return null;
	}
}

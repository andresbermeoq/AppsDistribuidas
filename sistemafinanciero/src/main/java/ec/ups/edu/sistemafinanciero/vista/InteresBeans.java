package ec.ups.edu.sistemafinanciero.vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionInteresON;
import ec.ups.edu.sistemafinanciero.modelo.Interes;

@Named
@RequestScoped
public class InteresBeans {
	@Inject
	private GestionInteresON gInter;
	
	private Interes interes;
	private String tInteres;
	private List<Interes> intereses;

	@PostConstruct
	public void init() {
		interes = new Interes();
		intereses = new ArrayList<Interes>();
	}	
	public String doSave() {
		gInter.saveIntereses(interes);		
		clearInteres();
		return null;
	}
	public String clearInteres() {		
		interes.setTiempofin(0);
		interes.setTiempofin(0);
		interes.setPorcentaje(0.00);
		interes.setObservacion("");
		return null;
	}
	public void listar() {
		try {
			intereses = gInter.listInteres();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Interes getInteres() {
		return interes;
	}
	public void setInteres(Interes newInteres) {
		this.interes = newInteres;
	}
	public GestionInteresON getgInter() {
		return gInter;
	}
	public void setgInter(GestionInteresON gInter) {
		this.gInter = gInter;
	}
	public String gettInteres() {
		return tInteres;
	}
	public void settInteres(String tInteres) {
		this.tInteres = tInteres;
	}
	public List<Interes> getIntereses() {
		return intereses;
	}
	public void setIntereses(List<Interes> intereses) {
		this.intereses = intereses;
	}	
}
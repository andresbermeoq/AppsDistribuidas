package ec.ups.edu.sistemafinanciero.vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.query.hql.internal.HqlParser.UpperFunctionContext;

import com.sun.xml.bind.CycleRecoverable.Context;

import ec.ups.edu.sistemafinanciero.gestion.GestionInteresON;
import ec.ups.edu.sistemafinanciero.modelo.Interes;

@Named
@RequestScoped
public class interesBeans {
	
	private Interes interes;
	@Inject
	private GestionInteresON ginteres;
	private String tInteres;
	private List<Interes> intereses;
	
	@PostConstruct
	public void init() {
		interes = new Interes();
		intereses = new ArrayList<Interes>();
	}
	
	public String clearInteres() {
		interes.setTipo("1");
		interes.setObservacion("");
		interes.setPorcentaje(0.00);
		interes.setTiempoInicial(0);
		interes.setTiempofin(0);
		
		return "";
	}
	public void listar() {
		try {
			listaIntereses();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void listaIntereses() throws SQLException {
		try {
			List<Interes> lista = ginteres.listInteres();
			for (Interes inter : lista) {
				interes.setId(inter.getId());
				interes.setTipo(inter.getTipo());
				interes.setPorcentaje(inter.getPorcentaje());
				interes.setTiempoInicial(inter.getTiempoInicial());
				interes.setTiempofin(inter.getTiempofin());
				interes.setObservacion(inter.getObservacion());
				intereses.add(interes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String doSave() {
		System.out.println(interes);
		if (ginteres.saveIntereses(interes)!=false) {
			System.out.println("Datos guardados");
			FacesMessage sms = new FacesMessage(FacesMessage.SEVERITY_INFO,"Datos guardados correctamente", null);
			FacesContext.getCurrentInstance().addMessage("finter:txtobservacion", sms);
			clearInteres();
		}else {
			FacesMessage sms = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al guardar los datos.", null);
			FacesContext.getCurrentInstance().addMessage("finter", sms);
			System.out.println("No se ha podido guardar los datos.");
		}
		return null;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public GestionInteresON getGinteres() {
		return ginteres;
	}

	public void setGinteres(GestionInteresON ginteres) {
		this.ginteres = ginteres;
	}

	public List<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Interes> intereses) {
		this.intereses = intereses;
	}

	public String gettInteres() {
		return tInteres;
	}

	public void settInteres(String tInteres) {
		this.tInteres = tInteres;
	}
}

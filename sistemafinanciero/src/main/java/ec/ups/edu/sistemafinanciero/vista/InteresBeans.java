package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	@Inject
	private LoginBean session;

	@PostConstruct
	public void init() {
		if (session.getUser() != null) {
			interes = new Interes();
			intereses = new ArrayList<Interes>();
			listar();
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String doSave() {

		try {
			if (interes.getTipo().equals("0")) {
				FacesContext.getCurrentInstance().addMessage("formlist",
						new FacesMessage("Seleccione un tipo de interes"));
			}
			if (interes.getTipo().equals("1") || interes.getTipo().equals("2")) {
				if (interes.getTiempofin() != interes.getTiempoInicial()) {
					if (interes.getTiempofin() > 0) {
						if (interes.getTiempoInicial() >= 0) {
							System.out.println("intertar");
							if (gInter.saveIntereses(interes) == true) {
								clearInteres();
								listar();
							} else {
								FacesContext.getCurrentInstance().addMessage("formlist",
										new FacesMessage("Error al guardar"));
							}
						} else {
							FacesContext.getCurrentInstance().addMessage("formlist",
									new FacesMessage("El dia inicial debe ser mayor a cero"));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage("formlist",
								new FacesMessage("El ultimo dia debe ser mayor a cero"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage("formlist",
							new FacesMessage("El ultimo dia debe ser mayor al dia inicial"));
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formlist",
					new FacesMessage("Error " + e.getLocalizedMessage()));
		}
		return null;
	}

	public void eliminar(Interes interes) {
		try {
			System.out.println("interes: " + interes.toString());
			gInter.deleteInteres(interes);
			listar();
		} catch (Exception e) {
		}
	}

	/*
	 * Metodo para limpiar los campos.
	 */
	public String clearInteres() {
		interes.setTiempoInicial(0);
		interes.setTiempofin(0);
		interes.setPorcentaje(0.00);
		interes.setObservacion("");
		return null;
	}

	/*
	 * Metodo para listar los intereses ya registrados.
	 */
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
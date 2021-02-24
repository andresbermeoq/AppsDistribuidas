package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionPolizaON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Poliza;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class CaptacionesBean implements Serializable {
	@Inject
	private LoginBean session;
	@Inject
	private GestionPolizaON gpolizaon;
	@Inject
	private GestionTransaccionON gtransaccionon;

	private List<Poliza> listaaprobar;
	private Poliza poliza;

	@PostConstruct
	public void init() {
		if (session.getUser() != null) {
			poliza = new Poliza();
			listaaprobar = new ArrayList<Poliza>();
			listPendAprob();
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

	public void listPendAprob() {
		try {
			listaaprobar = gpolizaon.listPoliza(1);
			for (Poliza poliza : listaaprobar) {
				System.out.println(poliza.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aprobar(Poliza poliza) {
		Transaccion transaccion = new Transaccion();
		Cajero cajero = new Cajero();

		Date factual = new Date();
		this.poliza.setFaprobado(factual);
		this.poliza.setEstado(2);
		System.out.println("Aprobar " + poliza.toString());
		double saldoCliente = gtransaccionon.saldoActual(poliza.getCliente().getIdClienteLong());
		if (saldoCliente >= poliza.getCapital()) {
			try {
				gtransaccionon.transaccion(transaccion);
				if (gpolizaon.aprobarPoliza(poliza, session.getUser()) == true) {
					FacesContext.getCurrentInstance().addMessage("formpolizas",
							new FacesMessage("La solicitud ha sido aprobada."));
					listPendAprob();
				} else {
					FacesContext.getCurrentInstance().addMessage("formpolizas",
							new FacesMessage("No se ha podido aprobar la solicitud."));
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("formpolizas",
						new FacesMessage("No se ha podido aprobar la solicitud. Error. " + e.getLocalizedMessage()));
				e.printStackTrace();
			}
		}

	}

	public void rechazar(Poliza poliza) {
		try {
			if (gpolizaon.rechazarPoliza(poliza, session.getUser()) == true) {
				FacesContext.getCurrentInstance().addMessage("formpolizas",
						new FacesMessage("La solicitud ha sido rechazada."));
				listPendAprob();
			} else {
				FacesContext.getCurrentInstance().addMessage("formpolizas",
						new FacesMessage("No se ha podido rechazar la solicitud. "));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formpolizas",
					new FacesMessage("No se ha podido rechazar la solicitud. Error. " + e.getLocalizedMessage()));
			e.printStackTrace();
		}
	}

	public GestionPolizaON getGpolizaon() {
		return gpolizaon;
	}

	public void setGpolizaon(GestionPolizaON gpolizaon) {
		this.gpolizaon = gpolizaon;
	}

	public List<Poliza> getListaaprobar() {
		return listaaprobar;
	}

	public void setListaaprobar(List<Poliza> listaaprobar) {
		this.listaaprobar = listaaprobar;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}
}

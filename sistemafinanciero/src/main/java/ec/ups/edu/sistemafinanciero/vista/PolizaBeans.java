package ec.ups.edu.sistemafinanciero.vista;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.NumberFormat;

import ec.ups.edu.sistemafinanciero.gestion.GestionInteresON;
import ec.ups.edu.sistemafinanciero.gestion.GestionPolizaON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.AsesorCta;
import ec.ups.edu.sistemafinanciero.modelo.Interes;
import ec.ups.edu.sistemafinanciero.modelo.Poliza;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
@RequestScoped
public class PolizaBeans {
	
	@Inject
	private GestionPolizaON gpoliza;
	@Inject
	private GestionInteresON ginteres;
	@Inject
	private GestionUsuarioON guser;
	@Inject
	private LoginBean session;
		
	private Usuario user;
	private Poliza poliza;
	private Interes interes;
	private double valInteres;
	private double valTotal;
	private Date thisDate;
	private Date finDate;
	private int plazo;
	private AsesorCta acta;
	
	@PostConstruct
	public void init() {
		poliza = new Poliza();
		interes = new Interes();
		acta = new AsesorCta();
		thisDate = new Date();
	}
	public void verUser() {
		System.out.println(session.getUser().getNombreUsuarioString());
	}
	public void calInteres() {
		try {
			NumberFormat formatter = new DecimalFormat("#0.00");
			Calendar calendar = Calendar.getInstance();
			interes = ginteres.searchToDay(plazo, "2");
			if (interes!=null) {
				poliza.setInteres(interes);
				poliza.setPlazo(plazo);
				
				double porcentaje = interes.getPorcentaje();
				
				try {
					double iGanado = (porcentaje * poliza.getCapital())/100;
					double total = iGanado + poliza.getCapital();
					poliza.setVinteres(iGanado);
					calendar.add(Calendar.DAY_OF_YEAR, poliza.getPlazo());
					poliza.setFvencimiento(calendar.getTime());
					poliza.setTotal(total);
					
				} catch (ArithmeticException e) {
					e.printStackTrace();
				}
			}else {
				FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						 "No se ha encontrado datos registrados", null);
						 FacesContext.getCurrentInstance().addMessage("formsolicitud:sms", javaTextMsg);
			}
		} catch (Exception e) {
			
		}
	}
	public boolean solPoliza() {		
		try {
			acta.setId(Long.parseLong("1"));
			poliza.setId(user.getIdUsuarioLong());
			poliza.setAsesorCta(acta);
			gpoliza.savePoliza(poliza);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public GestionPolizaON getGpoliza() {
		return gpoliza;
	}
	public void setGpoliza(GestionPolizaON gpoliza) {
		this.gpoliza = gpoliza;
	}
	public Poliza getPoliza() {
		return poliza;
	}
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public GestionInteresON getGinteres() {
		return ginteres;
	}

	public void setGinteres(GestionInteresON ginteres) {
		this.ginteres = ginteres;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public double getValInteres() {
		return valInteres;
	}

	public void setValInteres(double valInteres) {
		this.valInteres = valInteres;
	}

	public double getValTotal() {
		return valTotal;
	}

	public void setValTotal(double valTotal) {
		this.valTotal = valTotal;
	}

	public Date getThisDate() {
		return thisDate;
	}

	public void setThisDate(Date thisDate) {
		this.thisDate = thisDate;
	}

	public Date getFinDate() {
		return finDate;
	}

	public void setFinDate(Date finDate) {
		this.finDate = finDate;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public GestionUsuarioON getGuser() {
		return guser;
	}

	public void setGuser(GestionUsuarioON guser) {
		this.guser = guser;
	}
	public AsesorCta getActa() {
		return acta;
	}
	public void setActa(AsesorCta acta) {
		this.acta = acta;
	}
	
}

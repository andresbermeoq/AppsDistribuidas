package ec.ups.edu.sistemafinanciero.vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.NumberFormat;

import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionInteresON;
import ec.ups.edu.sistemafinanciero.gestion.GestionPolizaON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.AsesorCta;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
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
	private LoginBean session;
	@Inject
	private GestionClienteON gclienteon;
	@Inject
	private GestionTransaccionON gtransaccionon;

	private Usuario user;
	private Poliza poliza;
	private Cliente cliente;
	private AsesorCta acta;
	private Interes interes;

	private String copiaCedula;
	private String copiaPlanilla;

	private double capital;
	private int plazo;
	private double porcentinter;
	private double valInteres;
	private Date thisDate;
	private Date finDate;
	private double valTotal;

	private boolean show;

	@PostConstruct
	public void init() {
		if (session.getUser() != null) {
			cliente = new Cliente();
			poliza = new Poliza();
			interes = new Interes();
			acta = new AsesorCta();
			thisDate = new Date();
			show = false;
		} else {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("/sistemafinanciero/faces/templates/login.xhtml?faces-redirect=true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para buscar el interes de acuerdo al plazo.
	 */
	public void calInteres() {
		try {
			NumberFormat formatter = new DecimalFormat("#0.00");
			Calendar calendar = Calendar.getInstance();
			this.interes = ginteres.searchToDay(plazo, "2");
			if (this.capital > 0) {
				if (this.plazo > 0) {
					if (this.interes != null) {

						double porcentaje = this.interes.getPorcentaje();

						try {
							double iGanado = (porcentaje * this.capital) / 100;
							double total = iGanado + this.capital;
							this.poliza.setVinteres(iGanado);
							calendar.add(Calendar.DAY_OF_YEAR, plazo);

							this.porcentinter = porcentaje;
							this.valInteres = iGanado;
							this.finDate = calendar.getTime();
							this.valTotal = total;
							show = true;
						} catch (ArithmeticException e) {
							FacesContext.getCurrentInstance().addMessage("formsolicitud",
									new FacesMessage("Se ha generado un error." + e.getLocalizedMessage()));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage("formsolicitud", new FacesMessage(
								"No se puede calcular los intereses \n " + "debido a que no hay registrado ninguno."));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage("formsolicitud",
							new FacesMessage("Ingrese un plazo mayor a cero por favor"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("formsolicitud",
						new FacesMessage("Ingrese un capital mayor a cero por favor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("formsolicitud",
					new FacesMessage("Error." + e.getLocalizedMessage()));
		}
	}

	/**
	 * Metodo para registrar la solicitud de la poliza.
	 */
	public void solPoliza() {
		try {
			this.interes = ginteres.searchToDay(this.plazo, "2");
			copiaCedula = "...";
			copiaPlanilla = "...";
			this.acta.setId(Long.parseLong("1"));// Id del usuario de captacion.
			this.user = session.getUser();
			cliente = gclienteon.buscar(user.getCedulaString());
			double saldo = gtransaccionon.saldoActual(cliente.getIdClienteLong());
			if (capital <= saldo) {
				if (capital > 0 && valInteres > 0 && porcentinter > 0 && plazo > 0) {
					System.out.println("todo validado");
					this.cliente.setUsuario(this.user);
					this.poliza.setFsolicita(this.thisDate);
					this.poliza.setFvencimiento(this.finDate);
					this.poliza.setEstado(1);// 1: GENERADO 2:APROVADO 3:RECHAZADO
					this.poliza.setVinteres(this.valInteres);
					this.poliza.setTotal(this.valTotal);
					this.poliza.setCliente(cliente);
					this.poliza.setPlazo(plazo);
					this.poliza.setAsesorCta(acta);
					this.poliza.setCapital(getCapital());
					this.poliza.setVinteres(valInteres);
					this.poliza.setInteres(interes);
					this.poliza.setCedula(copiaCedula);
					this.poliza.setPlanilla(copiaPlanilla);

					if (gpoliza.savePoliza(this.poliza)) {
						FacesContext.getCurrentInstance().addMessage("formsolicitud",
								new FacesMessage("Su solicitud ha sido registrada."));
						vaciar();
					} else {
						FacesContext.getCurrentInstance().addMessage("formsolicitud",
								new FacesMessage("Se ha generado un error al registrar la solicitud."));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage("formsolicitud",
							new FacesMessage("Complete todo los datos por favor."));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("formsolicitud",
						new FacesMessage("Lo sentimos no cuenta con el saldo diponible en la cuenta para realizar \n"
								+ "una solicitud de la poliza, usted cuenta con un saldo de $" + saldo));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formsolicitud",
					new FacesMessage("Se ha generado un error al realizar la solicitud." + e.getLocalizedMessage()));
		}
	}

	/**
	 * Metodo para vaciar los campos
	 */
	public void vaciar() {
		this.valInteres = 0;
		this.capital = 0;
		this.porcentinter = 0;
		this.valTotal = 0;
		this.plazo = 0;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public AsesorCta getActa() {
		return acta;
	}

	public void setActa(AsesorCta acta) {
		this.acta = acta;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getCopiaCedula() {
		return copiaCedula;
	}

	public void setCopiaCedula(String copiaCedula) {
		this.copiaCedula = copiaCedula;
	}

	public String getCopiaPlanilla() {
		return copiaPlanilla;
	}

	public void setCopiaPlanilla(String copiaPlanilla) {
		this.copiaPlanilla = copiaPlanilla;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getPorcentinter() {
		return porcentinter;
	}

	public void setPorcentinter(double porcentinter) {
		this.porcentinter = porcentinter;
	}

	public GestionPolizaON getGpoliza() {
		return gpoliza;
	}

	public void setGpoliza(GestionPolizaON gpoliza) {
		this.gpoliza = gpoliza;
	}

	public GestionInteresON getGinteres() {
		return ginteres;
	}

	public void setGinteres(GestionInteresON ginteres) {
		this.ginteres = ginteres;
	}

	public LoginBean getSession() {
		return session;
	}

	public void setSession(LoginBean session) {
		this.session = session;
	}

}

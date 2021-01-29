package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "Polizas", schema = "public")
public class Poliza implements Serializable{

	@Id
	@SequenceGenerator(name = "pol_id_seq", sequenceName = "pol_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pol_id_seq")
	@Column(name = "pol_id", updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(name = "pol_capital", nullable = false)
	private double capital;
	
	@Column(name = "pol_plazo", nullable = false)
	private int plazo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pol_fk_int_id")
	private Interes interes;
	
	@Column(name = "pol_vinteres", nullable = false, precision = 2)
	private double vinteres;
	
	@Column(name = "pol_total", nullable = false)
	private double total;
	
	@Column(name = "pol_estado", nullable = false)
	private int estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pol_fsolicitada", nullable = false)
	private Date fsolicita;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pol_faprobado")
	private Date faprobado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pol_fvencimiento")
	private Date fvencimiento;
	
	@Column(name = "pol_observacion", length = 50)
	private String observacion;
	
	@Column(name = "pol_cedula", length = 250)
	private String cedula;
	
	@Column(name = "pol_plantilla", length = 250)
	private String planilla;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pol_asesor")
	private AsesorCta asesorCta;
	
	public Poliza() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFsolicita() {
		return fsolicita;
	}

	public void setFsolicita(Date fsolicita) {
		this.fsolicita = fsolicita;
	}

	public Date getFaprobado() {
		return faprobado;
	}

	public void setFaprobado(Date faprobado) {
		this.faprobado = faprobado;
	}

	public Date getFvencimiento() {
		return fvencimiento;
	}

	public void setFvencimiento(Date fvencimiento) {
		this.fvencimiento = fvencimiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPlanilla() {
		return planilla;
	}

	public void setPlanilla(String planilla) {
		this.planilla = planilla;
	}

	public AsesorCta getAsesorCta() {
		return asesorCta;
	}

	public void setAsesorCta(AsesorCta asesorCta) {
		this.asesorCta = asesorCta;
	}

//<<<<<<< HEAD
	public double getVinteres() {
		return vinteres;
	}

	public void setVinteres(double vinteres) {
		this.vinteres = vinteres;
	}

//=======
	@Override
//>>>>>>> AngelJadan
	public String toString() {
		return "Poliza [id=" + id + ", capital=" + capital + ", plazo=" + plazo + ", interes=" + interes + ", vinteres="
				+ vinteres + ", total=" + total + ", estado=" + estado + ", fsolicita=" + fsolicita + ", faprobado="
				+ faprobado + ", fvencimiento=" + fvencimiento + ", observacion=" + observacion + ", cedula=" + cedula
				+ ", planilla=" + planilla + ", asesorCta=" + asesorCta + "]";
	}
	
}

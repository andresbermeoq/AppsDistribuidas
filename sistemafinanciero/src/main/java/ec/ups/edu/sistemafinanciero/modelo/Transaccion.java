package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Transacciones", schema = "public")
public class Transaccion implements Serializable{
	
	private static final long SerialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "tra_id_seq", sequenceName = "tra_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tra_id_seq")
	@Column(name = "tra_id", updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(name = "tra_operacion", nullable = false, length = 10)
	private String operacion;
	
	@Column(name = "tra_cajero")
	private Cajero cajero;
	@Column(name = "tra_identificacion")
	private String identificacion;
	
	@Column(name = "tra_name", length = 100)
	private String nombre;
	
	/*@ManyToOne
	@JoinColumn(name = "tra_fk_cliente")
	private Cliente cliente;*/
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tra_fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "tra_monto", nullable = false)
	private double monto;
	
	@Column(name = "tra_agencia", nullable = false, length = 50)
	private String agencia;
	
	@Column(name = "tra_observacion", nullable = false, length = 100)
	private String observacion;
	
	@Column(name = "tra_santerior", nullable = false)
	private double saldoAnterior;
	
	@Column(name = "tra_sactual", nullable = false)
	private double sladoActual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Cajero getCajero() {
		return cajero;
	}

	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public double getSladoActual() {
		return sladoActual;
	}

	public void setSladoActual(double sladoActual) {
		this.sladoActual = sladoActual;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}
	
	
}

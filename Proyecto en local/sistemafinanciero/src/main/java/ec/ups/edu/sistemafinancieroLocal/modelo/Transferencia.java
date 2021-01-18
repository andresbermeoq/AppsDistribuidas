package ec.ups.edu.sistemafinancieroLocal.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Transferencias", schema = "public")
public class Transferencia implements Serializable{
	
	private static final long SerialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "taf_id_seq", sequenceName = "taf_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "taf_id_seq")
	@Column(name = "taf_id", updatable = false, unique = true, nullable = false)
	private Long id;
	
	/**
	 * Monto de la transferencia interna o externa.
	 */
	@Column(name = "taf_monto", nullable = false)
	private double monto;
	
	@OneToOne
	@JoinColumn(name = "taf_fk_transaccion")
	private Transaccion transaccion;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "taf_fk_interbancario")
	private Interbancario interbancario;
	
	public Transferencia() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Interbancario getInterbancario() {
		return interbancario;
	}

	public void setInterbancario(Interbancario interbancario) {
		this.interbancario = interbancario;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}
	
}

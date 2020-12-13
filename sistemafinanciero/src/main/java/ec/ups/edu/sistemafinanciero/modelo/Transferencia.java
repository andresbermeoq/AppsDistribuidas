package ec.ups.edu.sistemafinanciero.modelo;

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

@Entity
public class Transferencia implements Serializable{
	
	private static final long SerialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "taf_id_seq", sequenceName = "taf_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taf_id_seq")
	@Column(name = "taf_id", updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(name = "taf_monto", nullable = false)
	private double monto;
	
	@OneToOne
	@JoinColumn(name = "taf_fk_transaccion")
	private Transaccion transaccion;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "taf_fk_interbancario")
	private Interbancario interbancario;
	
	public Transferencia() {
		// TODO Auto-generated constructor stub
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
	
}

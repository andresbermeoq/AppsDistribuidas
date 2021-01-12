package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;

<<<<<<< HEAD
import javax.persistence.CascadeType;
=======
>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
=======
>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name = "Transferencias", schema = "public")
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
=======
@Table(name = "TRANSFERENCIA")
public class Transferencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "id_transferencia_seq", sequenceName = "id_transferencia_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_transferencia_seq")
	private Long idTransferencia;
	@Column(name = "monto_transferencia")
	private double monto;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaccion_fk")
	private Transaccion transaccion_fk;

	public Long getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(Long idTransferencia) {
		this.idTransferencia = idTransferencia;
>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Transaccion getTransaccion() {
<<<<<<< HEAD
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
	
=======
		return transaccion_fk;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion_fk = transaccion;
	}

	@Override
	public String toString() {
		return "Transferencia [idTransferencia=" + idTransferencia + ", monto=" + monto + ", transaccion=" + transaccion_fk
				+ "]";
	}
	
	

>>>>>>> 4f91a000319243aed4ecae0435228b004ac742e6
}

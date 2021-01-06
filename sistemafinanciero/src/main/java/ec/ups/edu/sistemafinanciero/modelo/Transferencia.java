package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Transferencia", schema = "public")
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
	private Transaccion transaccion;

	public Long getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(Long idTransferencia) {
		this.idTransferencia = idTransferencia;
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

	@Override
	public String toString() {
		return "Transferencia [idTransferencia=" + idTransferencia + ", monto=" + monto + ", transaccion=" + transaccion
				+ "]";
	}
	
	

}

package ec.ups.edu.sistemafinancieroLocal.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Interes", schema = "public")
public class Interes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "int_id_seq", sequenceName = "int_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "int_id_seq")
	@Column(name = "int_id", updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(name="int_tipo", length = 10, nullable = false)
	private String tipo;
	
	@Column(name = "int_porcentaje", nullable = false)
	private double porcentaje;
	
	@Column(name = "int_tinicial", nullable = false)
	private int tiempoInicial;
	
	@Column(name = "ini_tfin", nullable = false)
	private int tiempofin;
	
	@Column(name = "ini_observacion")
	private String observacion;
	
	public Interes() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getTiempoInicial() {
		return tiempoInicial;
	}

	public void setTiempoInicial(int tiempoInicial) {
		this.tiempoInicial = tiempoInicial;
	}

	public int getTiempofin() {
		return tiempofin;
	}

	public void setTiempofin(int tiempofin) {
		this.tiempofin = tiempofin;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public String toString() {
		return "Interes [id=" + id + ", tipo=" + tipo + ", porcentaje=" + porcentaje + ", tiempoInicial="
				+ tiempoInicial + ", tiempofin=" + tiempofin + ", observacion=" + observacion + "]";
	}
	
}

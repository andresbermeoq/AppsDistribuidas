package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "Acceso", schema = "public")
public class acceso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "id_acceso_seq", sequenceName = "id_acceso_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_acceso_seq")
	@Column(name = "id_acceso", updatable = false, unique = true, nullable = false)
	private Long idAccesoLong;
	
	@Column(name = "acceso_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessDate;
	
	@Column(name = "acceso_tipo")
	private String typeAccessString;
	@Column(name = "acceso_observacion")
	private String observationString;
	
	
	public acceso() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public acceso(Date accessDate, String typeAccessString, String observationString) {
		super();
		this.accessDate = accessDate;
		this.typeAccessString = typeAccessString;
		this.observationString = observationString;
	}
	
	public Long getIdAccesoLong() {
		return idAccesoLong;
	}

	public void setIdAccesoLong(Long idAccesoLong) {
		this.idAccesoLong = idAccesoLong;
	}

	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	public String getTypeAccessString() {
		return typeAccessString;
	}
	public void setTypeAccessString(String typeAccessString) {
		this.typeAccessString = typeAccessString;
	}
	public String getObservationString() {
		return observationString;
	}
	public void setObservationString(String observationString) {
		this.observationString = observationString;
	}
	
}
package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Acceso", schema = "public")
public class acceso implements Serializable {
	
	
	@Id
	@SequenceGenerator(name = "acceso_id_seq", sequenceName = "acceso_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acceso_id_seq")
	@Column(name = "acceso_id", updatable = false, unique = true, nullable = false)
	private Long idLong;
	@Column(name = "acceso_fecha")
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
	
	public Long getIdLong() {
		return idLong;
	}
	public void setIdLong(Long idLong) {
		this.idLong = idLong;
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
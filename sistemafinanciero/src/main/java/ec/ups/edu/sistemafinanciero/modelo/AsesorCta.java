package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AsesorCuentas", schema = "Public")
public class AsesorCta implements Serializable {
	@Id
	@SequenceGenerator(name = "asc_id_seq", sequenceName = "asc_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asc_id_seq")
	@Column(name = "asc_id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

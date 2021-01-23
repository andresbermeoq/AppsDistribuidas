package ec.ups.edu.sistemafinancieroLocal.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Cajero {
	@SequenceGenerator(name = "id_cajero_seq", sequenceName = "id_cajero_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cajero_seq")
	@Column(name = "id_cajero", updatable = false, unique = true, nullable = false)
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cajero_usu_fk")
	private Usuario usuario;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

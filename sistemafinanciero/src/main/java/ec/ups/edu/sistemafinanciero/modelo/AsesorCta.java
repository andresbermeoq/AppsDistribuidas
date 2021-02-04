package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "asc_usuario_fk")
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Set<Poliza> poliza;
	
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

	public Set<Poliza> getPoliza() {
		return poliza;
	}

	public void setPoliza(Set<Poliza> poliza) {
		this.poliza = poliza;
	}

	@Override
	public String toString() {
		return "AsesorCta [id=" + id + ", usuario=" + usuario + ", poliza=" + poliza + "]";
	}
}

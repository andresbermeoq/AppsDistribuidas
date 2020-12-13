package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import javassist.SerialVersionUID;

@Entity
public class Interbancario implements Serializable {
	private static final long SerialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "iba_id_seq", sequenceName = "iba_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iba_id_seq")
	@Column(name = "iba_id", updatable = false, unique = true, nullable = false)
	private Long id;
	
	@Column(name = "iba_banco", length = 100, nullable = false)
	private String banco;
	
	@Column(name = "iba_cuenta", nullable = false, length = 50)
	private String cuenta;
	
	@Column(name = "iba_tipocta", nullable = false, length = 20)
	private String tipoCta;
	
	@Column(name = "iba_titular", nullable = false, length = 100)
	private String nombreTitular;
	
	@Column(name = "iba_dni", nullable = false, length = 100)
	private String dniTitular;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "iba_fk_trasnferencia")
	private List<Transferencia> transferencia;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "iba_fk_cliente")
	private Cliente cliente;
}

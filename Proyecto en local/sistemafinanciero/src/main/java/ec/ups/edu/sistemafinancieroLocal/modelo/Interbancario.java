package ec.ups.edu.sistemafinancieroLocal.modelo;

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
import javax.persistence.Table;

import javassist.SerialVersionUID;

@Entity
@Table(name = "Interbancarios", schema = "public")
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
	/**
	 * Atributo para identificar si es de una cuenta interna o externa
	 */
	@Column(name = "iba_localizacion")
	private String localizacion;
	
	/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "iba_fk_trasnferencia")
	private List<Transferencia> transferencia;
	*/
	public Interbancario() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTipoCta() {
		return tipoCta;
	}

	public void setTipoCta(String tipoCta) {
		this.tipoCta = tipoCta;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getDniTitular() {
		return dniTitular;
	}

	public void setDniTitular(String dniTitular) {
		this.dniTitular = dniTitular;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	/*
	public List<Transferencia> getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(List<Transferencia> transferencia) {
		this.transferencia = transferencia;
	}*/

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	@Override
	public String toString() {
		return "Interbancario [id=" + id + ", banco=" + banco + ", cuenta=" + cuenta + ", tipoCta=" + tipoCta
				+ ", nombreTitular=" + nombreTitular + ", dniTitular=" + dniTitular + ", localizacion=" + localizacion
				+ "]";
	}
}

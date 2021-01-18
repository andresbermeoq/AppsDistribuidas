package ec.ups.edu.sistemafinancieroLocal.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
@Table(name = "Cliente", schema = "public")
public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name = "id_cliente_seq", sequenceName = "id_cliente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cliente_seq")
	@Column(name = "id_cliente", updatable = false, unique = true, nullable = false)
	@Id
	private Long idClienteLong;
	
	@Column(name = "cliente_cuenta")
	private String cuenta;
	
	@Column(name = "cliente_tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name = "cliente_fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistroDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "cliente_usuario_fk")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cliente_poliza_fk")
	private Poliza poliza;
	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(String cuenta, String tipoCuenta, Date fechaRegistroDate, Usuario usuario) {
		super();
		this.cuenta = cuenta;
		this.tipoCuenta = tipoCuenta;
		this.fechaRegistroDate = fechaRegistroDate;
		this.usuario = usuario;
	}

	public String getCuenta() {
		return cuenta;
	}
	public Long getIdClienteLong() {
		return idClienteLong;
	}

	public void setIdClienteLong(Long idClienteLong) {
		this.idClienteLong = idClienteLong;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public Date getFechaRegistroDate() {
		return fechaRegistroDate;
	}
	public void setFechaRegistroDate(Date fechaRegistroDate) {
		this.fechaRegistroDate = fechaRegistroDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	@Override
	public String toString() {
		return "Cliente [idClienteLong=" + idClienteLong + ", cuenta=" + cuenta + ", tipoCuenta=" + tipoCuenta
				+ ", fechaRegistroDate=" + fechaRegistroDate + ", usuario=" + usuario + ", poliza=" + poliza + ", id="
				+ "]";
	}
	
	
/*>>>>>>> AngelJadan
=======
	
	
	
>>>>>>> bd3397e6baa78f2c8fc2e67463b70f5ca08fa57a
*/
}

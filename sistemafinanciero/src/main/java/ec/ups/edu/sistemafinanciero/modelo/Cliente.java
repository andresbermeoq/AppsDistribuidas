package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Id
/*<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> bd3397e6baa78f2c8fc2e67463b70f5ca08fa57a
*/
	
	@SequenceGenerator(name = "id_cliente_seq", sequenceName = "id_cliente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cliente_seq")
	@Column(name = "id_cliente", updatable = false, unique = true, nullable = false)
	private Long idClienteLong;
	@Column(name = "cliente_cuenta")
	private String cuenta;
	
	@Column(name = "cliente_tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name = "cliente_fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistroDate;
	
	@ManyToOne
	@JoinColumn(name = "cliente_usuario_fk")
	private Usuario usuario;
	

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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> AngelJadan
/*<<<<<<< HEAD
=======
*/
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
<<<<<<< HEAD
	}
=======
=======
>>>>>>> AngelJadan

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
<<<<<<< HEAD
=======
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
>>>>>>> AngelJadan
	}
	
/*>>>>>>> AngelJadan
=======
	
	
	
>>>>>>> bd3397e6baa78f2c8fc2e67463b70f5ca08fa57a
*/
}

package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.sql.Date;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class Usuario implements Serializable {
	
	private Long idUsuarioLong;
	private String nameString;
	private String passwordString;
	private Date registrationDate;
	private boolean estado;
}

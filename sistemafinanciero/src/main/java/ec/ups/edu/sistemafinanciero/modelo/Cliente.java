package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {
	@Id
	private Long id;
}

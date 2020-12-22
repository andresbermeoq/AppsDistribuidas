package ec.ups.edu.sistemafinanciero.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;

@Named
@RequestScoped
public class ClienteBean {

	
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private Cliente cliente;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String doGuardarCliente() {
		
		gestionUsuarioON.saveCliente(cliente);
		return null;
	}
	
	
	
	
}

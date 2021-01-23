package ec.ups.edu.sistemafinancieroLocal.vista;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinancieroLocal.exceptions.GeneralException;
import ec.ups.edu.sistemafinancieroLocal.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinancieroLocal.modelo.Cliente;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;

@Named
@RequestScoped
public class ClienteBean {

	
	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private Cliente cliente;
	
	private List<Usuario> listaUsuarios;
	
	@PostConstruct
	public void init() {
		cliente = new Cliente();
		cargarListas();
		
	}
	
	public void cargarListas() {
		try {
			setListaUsuarios(gestionUsuarioON.obtenerUsuarios());
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
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

package ec.ups.edu.sistemafinanciero.gestion;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.dao.ClienteDAO;
import ec.ups.edu.sistemafinanciero.dao.UsuarioDAO;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Named
public class GestionClienteON {
	@Inject
	private ClienteDAO clientedao;
	private Cliente cliente;
	@Inject
	private UsuarioDAO usuariodao;
	private Usuario usuario;
	
	@Inject
	private GestionUsuarioON gesUsuario;
	
	public GestionClienteON() {
		cliente = new Cliente();
	}
	
	public boolean insertCliente(Cliente cliente) {
		return true;
	}
	public boolean update(Cliente cliente) {
		return true;
	}
	public Cliente buscarCliente(String cedul, String cuenta) {
		Cliente newCliente = new Cliente();
		Usuario user = new Usuario();
		try {
			user = usuariodao.read(cedul);
			if (user!=null) {
				newCliente = clientedao.buscar(user.getIdUsuarioLong(),cuenta);
			}
			
		} catch (Exception e) {
			new Exception("Se ha generado un error al buscar el cliente"+e.getLocalizedMessage());
		}finally {
			return newCliente;
		}
	}
}

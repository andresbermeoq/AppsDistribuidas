package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.sistemafinanciero.dao.ClienteDAO;
import ec.ups.edu.sistemafinanciero.dao.UsuarioDAO;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class GestionUsuarioON {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ClienteDAO clienteDAO;
		
	
	public boolean saveUsuario(Usuario isUsuario) {
		
		try {
			usuarioDAO.guardarUsuario(isUsuario);
		} catch (SQLException e) {
			System.out.println("Error Gestion Usuario:" + e.getMessage());
		}
		return true;
	}
	
	public boolean saveCliente(Cliente isCliente) {
		try {
			clienteDAO.guardarCliente(isCliente);
		} catch (Exception e) {
			System.out.println("Error Gestion Usuario: "+e.getMessage());
		}
		return true;
	}
	
	public Usuario buscarUsuario(String usuario) {
		Usuario user = new Usuario();
		user = usuarioDAO.readUsuario(usuario);
		return user;
	}
	
	public void sendEmail() {
		
	}
	
}

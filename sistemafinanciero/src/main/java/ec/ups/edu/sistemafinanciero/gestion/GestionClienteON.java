package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;

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
		System.out.println("busqueda gestion");
		try {
			user = usuariodao.read(cedul);
			System.out.println("user read encontrado "+user.toString());
			if (user!=null) {
				newCliente = clientedao.buscar(user.getIdUsuarioLong(),cuenta);
				System.out.println("buscarCliente return"+ newCliente.toString());
			}
		}
		catch (SQLException ex) {
			new Exception("Cliente no encontrado"+ex.getLocalizedMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			new Exception("Se ha generado un error al buscar el cliente"+e.getLocalizedMessage());
		}finally {
			return newCliente;
		}
	}
}

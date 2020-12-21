package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

import ec.ups.edu.sistemafinanciero.dao.ClienteDAO;
import ec.ups.edu.sistemafinanciero.dao.UsuarioDAO;
import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.modelo.Acceso;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.utils.MailUtil;

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

	public Usuario validarUsuarioAdmin(String usuario, String password) throws GeneralException {
		Usuario usuarioAdmin = usuarioDAO.obtenerUsuarioCorreoAdmin(usuario);
		
		if(usuarioAdmin.getPasswordString().equals(password)) {
			usuarioAdmin.setPasswordString("");
			usuarioAdmin.setNombreUsuarioString(usuario);
			usuarioAdmin.setAdmin(false);
			return usuarioAdmin;
		}else {
			throw new GeneralException(201, "Password Incorrecto");
		}
	}
	
<<<<<<< HEAD
	public Usuario buscarUsuario(String usuario) {
		Usuario user = new Usuario();
		user = usuarioDAO.readUsuario(usuario);
		return user;
	}
	
	public void sendEmail() {
=======
	public void enviarCorreo(Usuario usuario, String dispositivo, String ubicacion, boolean correcto) {
		
		String asuntoMensaje = "Intento de Acceso a la Banca Virtual";
>>>>>>> 9759a4cd3e8fb5f66e177c848c2e968f30f87f80
		
		StringBuilder cuerpoMensaje = new StringBuilder("Querido Usuario: <strong>");
		cuerpoMensaje.append(usuario.getNombre()).append("</strong><br>")
						.append("SISTEMA FINANCIERO notifica a Ud. la siguiente informacion <br>")
						.append("========================================================== <br>");
		
		if (correcto) {
			cuerpoMensaje.append("<strong> INTENTO DE ACCESO SATISFACTORIO </strong><br>");
		}else {
			cuerpoMensaje.append("<strong> INTENTO DE ACCESO FALLIDO </strong><br>");
			cuerpoMensaje.append("<strong> IP ADDRES: </strong> ").append(ubicacion).append("<br>")
					.append("<strong> DISPOSITIVO: </strong> ").append(dispositivo).append( "<br>")
					.append("========================================================== <br>");
		}
		
		CompletableFuture.runAsync(() -> {
				try {
					MailUtil.enviarCorreo(usuario.getEmail(), asuntoMensaje, cuerpoMensaje.toString());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
		});
	}
	
	public List<Acceso> obtenerAccesos (long idUsuario) throws GeneralException {
		return clienteDAO.obtenerAccesoClientes(idUsuario);
	}
}

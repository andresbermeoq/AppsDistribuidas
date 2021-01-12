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
import ec.ups.edu.sistemafinanciero.utils.RandomUtil;

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
	
	public String generarPassword() {
		String passwordString = RandomUtil.generarPassword();
		return passwordString;
	}
	
	public boolean saveCliente(Cliente isCliente) {
		try {
			System.out.println("Cliente Guardado");
			clienteDAO.guardarCliente(isCliente);
			String numeroCuenta = RandomUtil.generarNumeroCuenta();
			System.out.println(numeroCuenta);
			enviarCorreoInicial(isCliente.getUsuario(), numeroCuenta);
		} catch (Exception e) {
			System.out.println("Error Gestion Usuario: "+e.getMessage());
		}
		return true;
	}

	public Usuario validarUsuarioAdmin(String usuario, String password) throws GeneralException {
		Usuario usuarioAdmin = usuarioDAO.obtenerUsuario(usuario);
		System.out.println("Usuario Admin: " + usuarioAdmin.toString());
		if(usuarioAdmin.getPasswordString().equals(password)) {
			System.out.println("Usuario ON2: "+usuarioAdmin.toString());
			return usuarioAdmin;
		}else {
			throw new GeneralException(201, "Password Incorrecto");
		}
	}
	public Usuario buscarUsuario(String usuario) {
		Usuario user = new Usuario();
		user = usuarioDAO.readUsuario(usuario);
		return user;
	}
	
	public void enviarCorreoInicial(Usuario usuarioCliente, String password) {
		String asuntoMensaje = "Acceso a la Banca Virtual";
		StringBuilder cuerpoMensaje = new StringBuilder("Estimado(a) Cliente/Usuario: <strong>")
				.append(usuarioCliente.getNombre()).append("</strong><br>")
				.append("SISTEMA FINANCIERO notifica a Ud. la siguiente informacion <br>")
				.append("========================================================== <br>")
				.append("<strong> USUARIO: </strong> ").append(usuarioCliente.getNombreUsuarioString()).append("<br>")
				.append("<strong> CONTRASEÑA: </strong> ").append(usuarioCliente.getPasswordString()).append("<br>")
				.append("========================================================== <br>");
		CompletableFuture.runAsync(() -> {
			try {
				MailUtil.enviarCorreo(usuarioCliente.getEmail(), asuntoMensaje, cuerpoMensaje.toString());
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
		});
	}
	
<<<<<<< HEAD
	public void enviarCorreo(Usuario usuario, String dispositivo, String ubicacion, boolean correcto) {
=======
<<<<<<< HEAD
	public Usuario buscarUsuario(String usuario) {
		Usuario user = new Usuario();
		user = usuarioDAO.readUsuario(usuario);
		return user;
	}
	
	public void sendEmail() {
=======
	public void enviarCorreo(Usuario usuario, String dispositivo, String ubicacion, boolean correcto) {
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
>>>>>>> AngelJadan
		
		String asuntoMensaje = "Intento de Acceso a la Banca Virtual";
		
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
	
	public List<Usuario> obtenerUsuarios() throws GeneralException {
		return usuarioDAO.obtenerTodosUsuarios();
	}
}

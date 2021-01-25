package ec.ups.edu.sistemafinanciero.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.ups.edu.sistemafinanciero.dao.UsuarioDAO;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
@Path("/cliente")
public class ClienteServicesRest {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<Usuario> obtenerUsuarios() {
		
		return null;
	}
	
}

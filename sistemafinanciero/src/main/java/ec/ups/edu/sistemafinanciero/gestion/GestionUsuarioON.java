package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.List;

public class GestionUsuarioON {
	public boolean saveUsuario() {
		return true;
	}
	public boolean updateUsuario() {
		return true;
	}
	public String searchUsuario() {
		return "";
	}
	public String genPassword() {
		return "";
	}
	public boolean sendEmailUsuario() {
		return true;
	}
	public boolean registrarAcceso() {
		return true;
	}
	public boolean enviarIntento() {
		return true;
	}
	public List<Object> listarAccesos() {
		List<Object> lista = new ArrayList<Object>();
		return lista;
	}
	public boolean blockUsuario() {
		return true;
	}
	public boolean unblockUsuario() {
		return true;
	}
}

package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.List;

public class GestionTransaccionON {

	public boolean registarDeposito(){
		return true;
	}
	public boolean registrarRetiro(){
		return true;
	}
	public List<Object> listarDeposito(){
		List<Object> list = new ArrayList<Object>();
		return list;
	}
	public List<Object> listarRetiro(){
		List<Object> list = new ArrayList<Object>();
		return list;
	}
	public boolean updateEstadoCta() {
		return true;
	}
	public boolean generarEstadoCuenta() {
		return true;
	}
	public boolean transferir() {
		return true;
	}
}

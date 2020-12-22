package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.parse.ResyncToEndOfRuleBlock;

import ec.ups.edu.sistemafinanciero.dao.PolizaDAO;
import ec.ups.edu.sistemafinanciero.modelo.Poliza;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

public class GestionPolizaON {
	
	private PolizaDAO pdao;
	
	public GestionPolizaON() {
		
	}
	public boolean savePoliza(Poliza poliza) throws Exception {
		try {
			pdao.insert(poliza);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Se ha generado un error al guardar la poliza.");
		}
	}
	public boolean validarPoliza(Poliza poliza, Long idTransaccion, Long idCliente ) {
		
		return true;
	}
	public float consultaSaldoCta() {
		float n = (float) 0.00;
		return n;
	}
	public String calculaValorPoliza(String poliza) {
		return "";
	}
	public boolean updatePoliza(Poliza poliza) throws Exception {
		try {
			pdao.update(poliza);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al actualizar la poliza");
		}
		return true;
	}
	public String searchPoliza() {
		return "";
	}
	public List<Object> listPoliza(){
		List<Object> lista = new ArrayList<Object>();
		return lista;
	}
	public boolean deletePoliza(int id) throws Exception {
		try {
			pdao.delete(id);
		} catch (Exception e) {
			throw new Exception("Se ha generado un error al borrar la poliza.");
		}
		return true;
	}
	public boolean aprobarPoliza(Poliza poliza, Usuario usuario) throws Exception {
		return true;
	}
}

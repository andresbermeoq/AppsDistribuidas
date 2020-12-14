package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.List;

import ec.ups.edu.sistemafinanciero.dao.InteresDAO;
import ec.ups.edu.sistemafinanciero.modelo.Interes;

public class GestionInteresON {
	private InteresDAO idao;
	
	public boolean saveIntereses(Interes interes) throws Exception {
		try {
			idao.insert(interes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Se ha generado un error al guardar los intereses.");
		}
		return true;
	}
	public String searchIntereses() {
		return "";
	}
	public double callInteres() {
		return 0.00;
	}
	public boolean deleteInteres() {
		return true;
	}
	public List<Object> listInteres(){
		List<Object> lista = new ArrayList<Object>();
		return lista;
	}
}

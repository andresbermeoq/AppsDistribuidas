package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.dao.InteresDAO;
import ec.ups.edu.sistemafinanciero.modelo.Interes;

@Named
public class GestionInteresON {
	@Inject
	private InteresDAO idao;

	public boolean saveIntereses(Interes interes) {
		try {
			if (interes.getTiempoInicial() > 0 && interes.getTiempofin() > 0
					&& interes.getTiempofin() != interes.getTiempoInicial() && interes.getPorcentaje() >= 0) {
				if (idao.insert(interes) == true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Interes searchInteres(long id) {
		Interes interes= new Interes();
		try {
			interes = idao.read(id);
			return interes;
		} catch (Exception e) {
			e.printStackTrace();
			return interes;
		}
	}
	public Interes searchToDay(int day, String tipo) {
		
		Interes interes = new Interes();
		try {
			interes = idao.readRange(day, tipo);
		} catch (Exception e) {
			System.out.println("Error en gestionInteres readRange.");
			e.printStackTrace();
		}finally {
			return interes;
		}
	}

	public boolean deleteInteres() {
		return true;
	}

	public List<Interes> listInteres() throws SQLException {

		List<Interes> lista = new ArrayList<Interes>();
		try {
			lista = idao.listIntTipo();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
}


package ec.ups.edu.sistemafinancieroLocal.gestion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinancieroLocal.dao.InteresDAO;
import ec.ups.edu.sistemafinancieroLocal.modelo.Interes;

@Named
public class GestionInteresON {
	@Inject
	private InteresDAO idao;

	/**
	 * 
	 * @param interes
	 * @return true, si se guarda, false y mensaje si no se guarda y da errores.
	 */
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
	
	/**
	 * 
	 * @param id del interes
	 * @return Interes encontrado
	 */
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
	/**
	 * 
	 * @param day Dia dentro del rango de un interes.
	 * @param tipo Tipo de interes('1' Credito/ '2' Poliza)
	 * @return Interes encontrado.
	 */
	public Interes searchToDay(int day, String tipo) {
		
		Interes interes = new Interes();
		try {
			interes = idao.readRange(day, tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return interes;
		}
	}
	/**
	 * 
	 * @param id Id del interes a eliminar
	 * @return Retorna true, si se elimina, false si no se elimina y genera algun error.
	 */
	public boolean deleteInteres(int id) {
		boolean resultado = false;
		try {
			resultado = idao.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			return resultado;
		}
	}
	/**
	 * 
	 * @return Retorna todos los intereses sean estes de creditos o polizas.
	 * @throws SQLException Captura errores de 
	 */
	public List<Interes> listInteres() throws SQLException {

		List<Interes> lista = new ArrayList<Interes>();
		try {
			lista = idao.listInteres();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
}

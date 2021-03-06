package ec.ups.edu.sistemafinancieroLocal.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.antlr.v4.parse.ResyncToEndOfRuleBlock;

import ec.ups.edu.sistemafinancieroLocal.dao.PolizaDAO;
import ec.ups.edu.sistemafinancieroLocal.modelo.Poliza;
import ec.ups.edu.sistemafinancieroLocal.modelo.Usuario;

@Named
public class GestionPolizaON {
	
	@Inject
	private PolizaDAO pdao;
	@Inject
	private GestionTransaccionON gtransaccionOn;
	@Inject
	private GestionClienteON gclienteOn;
	@Inject
	private GestionUsuarioON gusuarioOn;
	
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
	public boolean validarPoliza(Poliza poliza, Long idCliente ) {
		boolean estado = false;
		double saldo = gtransaccionOn.saldoActual(idCliente);
		if (saldo>=poliza.getCapital()) {
			return estado;
		}
		return estado;
	}
	public Poliza calculaValorPoliza(double capital, double interes, int plazo) {
		Poliza poliza = new Poliza();
		try {
			double iGanado = (interes * capital)/100;
			double total = iGanado + capital;
			poliza.setCapital(capital);
			poliza.setPlazo(plazo);
			poliza.setTotal(total);
			poliza.setVinteres(iGanado);
		} catch (ArithmeticException e) {
			new ArithmeticException("Error al calcular valores "+e.getLocalizedMessage());
		}
		return poliza;
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
	/**
	 * 
	 * @param estado APROBADO, cuando la poliza ya ha sido aprovada, PENDIENTE, y RECHAZADA.
	 * @return List<Poliza> las polizas con el estado de Cliente o 
	 */
	public List<Poliza> listPoliza(String estado){
		List<Poliza> lista = new ArrayList<Poliza>();
		lista = pdao.listPoliza(estado);
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

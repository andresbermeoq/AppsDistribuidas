package ec.ups.edu.sistemafinanciero.gestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.antlr.v4.parse.ResyncToEndOfRuleBlock;

import ec.ups.edu.sistemafinanciero.dao.PolizaDAO;
import ec.ups.edu.sistemafinanciero.modelo.AsesorCta;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Poliza;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.gestion.GestionClienteON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;

public class GestionPolizaON {
	
	@Inject
	private PolizaDAO pdao;
	@Inject
	private GestionTransaccionON gtransaccionOn;
	@Inject
	private GestionClienteON gclienteOn;
	@Inject
	private GestionUsuarioON gusuarioOn;
	
	private Date factual;
	
	public GestionPolizaON() {
		factual = new Date();
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
		boolean estado = false;
		try {
			pdao.update(poliza);
			estado = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al actualizar la poliza");
		}finally {
			return estado;
		}
	}
	public String searchPoliza() {
		return "";
	}
	/**
	 * 
	 * @param estado 2:APROBADO, cuando la poliza ya ha sido aprovada, 1:GENERADO, y 3:RECHAZADO.
	 * @return List<Poliza> las polizas con el estado de Cliente o 
	 */
	public List<Poliza> listPoliza(int estado){
		List<Poliza> lista = new ArrayList<Poliza>();
		lista = pdao.listPoliza(estado);
		return lista;
	}
	/**
	 * Metodo para listar las polizas del cliente segun el estado.
	 * @param clienteId
	 * @param estado 1:GENERADO, 2:APROVADO, 3:RECHAZADO
	 * @return
	 */
	public List<Poliza>listPolizaCliente(long clienteId, int estado){
		List<Poliza> lista = new ArrayList<Poliza>();
		lista = pdao.listPolizaCliente(clienteId,estado);
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
	/**
	 * 
	 * @param poliza Poliza a actualizar aprobando
	 * @param usuario Asistente de Captaciones.
	 * @return true, si se ha actualizado correctamente.
	 * @throws Exception
	 */
	public boolean aprobarPoliza(Poliza poliza, Usuario usuario) throws Exception {
		boolean estado = false;
		
		AsesorCta asesorcta = new AsesorCta();
		
		
		try {
			Transaccion transaccion = new Transaccion();
			Cajero cajero = new Cajero();
						
			double saldoDisponible = 0.00;
			if (usuario.getTipoString().equals("Asistente de Captaciones")) {
				try {
					saldoDisponible = gtransaccionOn.saldoActual(poliza.getCliente().getIdClienteLong());
					if (saldoDisponible>=poliza.getCapital()) {
						cajero.setId(1L);
						
						transaccion.setAgencia("EN LINEA");
						transaccion.setFecha(factual);
						transaccion.setIdentificacion(poliza.getCliente().getUsuario().getCedulaString());
						transaccion.setMonto(poliza.getCapital());
						transaccion.setNombre(poliza.getCliente().getUsuario().getNombre()+" "+poliza.getCliente().getUsuario().getApellido());
						transaccion.setOperacion("RETIRO");
						transaccion.setObservacion("N/D Poliza");
						transaccion.setCajero(cajero);
						transaccion.setCliente(poliza.getCliente());
						
						try {
							//Reaiza el retiro de la cuenta del cliente y valida que sea correcto
							if (gtransaccionOn.transaccion(transaccion)==true) {
								//Busca el id del asesor con el usuario recibido.
								asesorcta = gusuarioOn.buscarAsesorCta(usuario);
								//Verifica que el usuario no se nulo.
								if (asesorcta!=null) {
									
									poliza.setAsesorCta(asesorcta);
									poliza.setFaprobado(factual);
									poliza.setEstado(2);
									/*Actualiza la poliza cambiando el estado de 1:'GENERADO' a
									 * 2:'APROBADO'
									*/
									if (updatePoliza(poliza)==true) {
										estado = true;
									}else {
										estado = false;
									}
								}
							}else {
								estado = false;
							}
						} catch (Exception e) {
							estado = false;
							new Exception("No se pudo realizar el debito de la cuenta "+e.getLocalizedMessage());
							e.printStackTrace();
						}finally {
							return estado;
						}
						
					}else {
						estado = false;
					}
				} catch (Exception e) {
					estado=false;
					new Exception("Saldo insuficiente "+e.getLocalizedMessage());
				}finally {
					return estado;
				}
			}else {
				estado=false;
			}
		} catch (Exception e) {
			new Exception("Error "+e.getLocalizedMessage());
		}finally {
			return estado;
		}
	}
	/**
	 * 
	 * @param poliza Poliza solicitada.
	 * @param usuario Usuario de asesor de cuenta.
	 * @return
	 */
	public boolean rechazarPoliza(Poliza poliza, Usuario usuario) {
		boolean estado = false;
		AsesorCta asesorcta = new AsesorCta();
		Date factual = new Date();
		try {
			asesorcta = gusuarioOn.buscarAsesorCta(usuario);
			if (asesorcta!=null) {
				poliza.setEstado(3);
				poliza.setAsesorCta(asesorcta);
				poliza.setFaprobado(factual);
				poliza.setEstado(3);
				updatePoliza(poliza);
				estado = true;
			}
		} catch (Exception e) {
			new Exception();
		}finally {
			return estado;
		}
	}
	public Date getFactual() {
		return factual;
	}
	public void setFactual(Date factual) {
		this.factual = factual;
	}
	
}

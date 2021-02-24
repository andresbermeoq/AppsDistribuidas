package ec.ups.edu.sistemafinanciero.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import ec.ups.edu.sistemafinanciero.gestion.GestionPolizaON;
import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Poliza;
import ec.ups.edu.sistemafinanciero.modelo.Transaccion;

@Named
@Singleton
public class TareaProgramada {
	@Inject
	private GestionPolizaON gPolizaON;
	@Inject
	private GestionTransaccionON gTransaccionON;
	
	private List<Poliza> polizas;
	private Date factual;
	private Transaccion transaccion;

	private final Logger log = Logger.getLogger(getClass().getName());
	public TareaProgramada() {
		polizas = new ArrayList<Poliza>();
		factual = new Date();
		transaccion = new Transaccion();
	}

//@Schedule(hour = “0”, dayOfWeek = “*”, info = “Todos los dias a las 00:00”)
	@Schedule(second = "*", minute = "34", hour = "16", info = "Cada 5 minutos")
	public void performTask() {

		long timeInit = System.currentTimeMillis();
		long time = System.currentTimeMillis();

		log.info(":. Inicio TareaProgramada.");

		try {
			timeInit = System.currentTimeMillis();
			acreditacion();

// TODO Hacer la lógica de la tarea programada

		} catch (Exception e) {
			log.error("Error en la tarea programada");
			log.info(e);

			time = System.currentTimeMillis();
			log.info(":. Fin tarea programada. Tiempo de proceso = " + time);
		}
	}
	public void acreditacion() {
		try {
			polizas = gPolizaON.listPoliza(2);
			for (Poliza poliza : polizas) {
				if (poliza.getFvencimiento()==factual) {
					try {
						//4 Terminado
						poliza.setEstado(4);
						gPolizaON.updatePoliza(poliza);
						
						Cajero cajero = new Cajero();
						cajero.setId(1L);
						transaccion.setAgencia("EN LINEA");
						transaccion.setOperacion("DEPOSITO");
						transaccion.setCajero(cajero);
						transaccion.setCliente(poliza.getCliente());
						transaccion.setMonto(poliza.getTotal());
						transaccion.setObservacion("CREDITO POLIZA");
						transaccion.setFecha(factual);
						transaccion.setIdentificacion("na");
						transaccion.setNombre("Financiero");
						
						gTransaccionON.transaccion(transaccion);
						System.out.println("acreditando");
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GestionPolizaON getgPolizaON() {
		return gPolizaON;
	}

	public void setgPolizaON(GestionPolizaON gPolizaON) {
		this.gPolizaON = gPolizaON;
	}

	public GestionTransaccionON getgTransaccionON() {
		return gTransaccionON;
	}

	public void setgTransaccionON(GestionTransaccionON gTransaccionON) {
		this.gTransaccionON = gTransaccionON;
	}

	public List<Poliza> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}

	public Date getFactual() {
		return factual;
	}

	public void setFactual(Date factual) {
		this.factual = factual;
	}

	public Logger getLog() {
		return log;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}
	
}
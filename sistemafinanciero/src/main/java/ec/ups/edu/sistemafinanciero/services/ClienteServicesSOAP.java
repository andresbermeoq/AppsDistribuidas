package ec.ups.edu.sistemafinanciero.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.ups.edu.sistemafinanciero.gestion.GestionTransaccionON;

@WebService
public class ClienteServicesSOAP {
	
	private GestionTransaccionON gestionTransaccionON;
	
	@WebMethod
	public String deposito(double cantidad) {
		gestionTransaccionON.registrarDeposito(null, cantidad);
		return null;
	}

	@WebMethod
	public String retiro(double cantidad) {
		gestionTransaccionON.registrarDeposito(null, cantidad);
		return null;
	}
}

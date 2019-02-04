package ar.edu.ubp.das.src.clients;

import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public interface WSClient {

	public List<DynaActionForm> getOfertas(String authToken, String funcion, String url);
//	public void notificarTransaccion(TransactionBean transaccion);
}

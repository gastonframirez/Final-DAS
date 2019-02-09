package ar.edu.ubp.das.src.clients;

import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public interface WSClient {

	public List<DynaActionForm> getOfertas(String authToken, String url, String funcion);
	public String notificarTransaccion(DynaActionForm transaccion, String authToken, String url, String funcion) throws Exception;
}

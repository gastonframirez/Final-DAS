package ar.edu.ubp.das.src.admin.clients;

import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public interface WSClient {

	public Integer testConnection(String authToken, String url, String funcion) throws Exception;
	public String notificarMensaje(DynaActionForm mensaje, String authToken, String url, String funcion) throws Exception;

}

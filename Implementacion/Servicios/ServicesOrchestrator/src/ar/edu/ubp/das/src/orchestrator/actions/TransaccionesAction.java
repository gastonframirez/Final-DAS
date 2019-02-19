package ar.edu.ubp.das.src.orchestrator.actions;

import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clients.ClientFactory;
import ar.edu.ubp.das.src.clients.WSClient;
import ar.edu.ubp.das.src.orchestrator.forms.ComercioForm;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;
import ar.edu.ubp.das.src.orchestrator.forms.TransactionForm;

public class TransaccionesAction {
	public void init ()  throws SQLException{
		System.out.println("Iniciando conexion a servicios (transacciones)..");
		
		//Conectarse a cada comercio
		DynaActionForm form = new DynaActionForm();
		Dao daoComercios = DaoFactory.getDao( "Comercios", "ar.edu.ubp.das.src.orchestrator" );
		
		List<DynaActionForm> comercios = daoComercios.select(form);
		Dao daoLogs =  DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.orchestrator" );
		if(comercios.size()==0) {
			System.out.println("No existe ningun comercio habilitado al momento de enviar transacciones pendientes.");
		}
		for(DynaActionForm cf : comercios) {
			ComercioForm comercio = (ComercioForm)cf;

			form = new DynaActionForm();
			form.setItem("idComercio", comercio.getIdComercio().toString());
			
			// Conectarse a client
			try {

				Dao daoTransacciones = DaoFactory.getDao( "Transactions", "ar.edu.ubp.das.src.orchestrator" );
				form.setItem("idComercio", comercio.getIdComercio().toString());
				
				List<DynaActionForm> transacciones = daoTransacciones.select(form);
				
				for(DynaActionForm transaccion : transacciones) {
					try {
						WSClient comercioClient = ClientFactory.getClient(comercio.getJavaClass(), "ar.edu.ubp.das.src");
						String status = comercioClient.notificarTransaccion(transaccion, "Token "+comercio.getAuthToken(), 
									comercio.getBaseURL().get("transacciones"), comercio.getFuncion().get("transacciones"));
						if(status.equals("200")) {
							System.out.println(status);
							daoTransacciones.update(transaccion);
						}
					}catch (SQLException e) {
						//Loguear
						
						form.setItem("logStr", "Error en conexion al servicio del comercio ID:" + comercio.getIdComercio().toString() + 
								"al intentar obtener ofertas.");
						daoLogs.insert(form);
					}//
				}

								
			} catch (Exception e) {
				e.printStackTrace();
				//Guardar log de error log
				form.setItem("logStr", "Error al intentar obtener las transacciones para enviar a comercios.");
				daoLogs.insert(form);
			}
		}		
	}
}

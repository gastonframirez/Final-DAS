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
		System.out.println("Iniciando conexion a servicios...");
		
		//Conectarse a cada comercio
		DynaActionForm form = new DynaActionForm();
		Dao daoComercios = DaoFactory.getDao( "Comercios", "ar.edu.ubp.das.src.orchestrator" );
		
		List<DynaActionForm> comercios = daoComercios.select(form);

		for(DynaActionForm cf : comercios) {
			ComercioForm comercio = (ComercioForm)cf;

			form = new DynaActionForm();
			form.setItem("idComercio", comercio.getIdComercio().toString());
			
			// Conectarse a client
			try {
				System.out.println("Pidiendo ofertas al comercio");

				Dao daoTransacciones = DaoFactory.getDao( "Transactions", "ar.edu.ubp.das.src.orchestrator" );
				form.setItem("idComercio", comercio.getIdComercio().toString());
				
				List<DynaActionForm> transacciones = daoTransacciones.select(form);
				
				for(DynaActionForm transaccion : transacciones) {
					try {
						WSClient comercioClient = ClientFactory.getClient(comercio.getJavaClass(), "ar.edu.ubp.das.src");
						comercioClient.notificarTransaccion(transaccion, comercio.getAuthToken(), 
									comercio.getBaseURLTransacciones(), comercio.getFuncionTransacciones());
						daoTransacciones.update(transaccion);
					}catch (SQLException e) {
						//Loguear
					}
				}

								
			} catch (Exception e) {
				e.printStackTrace();
				//Guardar log de error log
			}
		}		
	}
}

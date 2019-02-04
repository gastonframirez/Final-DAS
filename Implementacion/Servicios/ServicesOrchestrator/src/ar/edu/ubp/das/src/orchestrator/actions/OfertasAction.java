package ar.edu.ubp.das.src.orchestrator.actions;

import java.sql.SQLException;

import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clients.WSClient;
import ar.edu.ubp.das.src.clients.ClientFactory;
import ar.edu.ubp.das.src.orchestrator.forms.ComercioForm;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;

public class OfertasAction {
	
	public void init () throws SQLException{
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
//				System.out.println(comercio.getFuncionOffers());
//				System.out.println(comercio.getBaseURLOffers());
//				System.out.println(comercio.getAuthToken());
				System.out.println(comercio.getJavaClass());
				
				WSClient comercioClient = ClientFactory.getClient(comercio.getJavaClass(), "ar.edu.ubp.das.src");
				
				System.out.println("Pidiendo ofertas al comercio");
				List<DynaActionForm> ofertas = comercioClient.getOfertas("Token " + comercio.getAuthToken(), comercio.getFuncionOffers(), comercio.getBaseURLOffers());

				Dao daoOfertas = DaoFactory.getDao( "Offers", "ar.edu.ubp.das.src.orchestrator" );
				
				//Guardar ofertas por cada comercio
				for(DynaActionForm oferta : ofertas) {
					OfferForm offer = (OfferForm) oferta;
					if(offer.getStatus().equals("200")) {
						form = new DynaActionForm();
						form.setItem("idComercio", comercio.getIdComercio().toString());
						form.setItem("fechaInicio", offer.getFechaInicio());
						form.setItem("fechaFin", offer.getFechaFin());
						form.setItem("imageURL", offer.getImageURL());
						form.setItem("ofertaURL", offer.getUrl());
						form.setItem("idOferta", offer.getIdOferta());
						daoOfertas.insert(form);
					}else {
						// Guardar error en log
						System.out.println("Error en conexion a comercio");
					}
					
				}
								
			} catch (Exception e) {
				e.printStackTrace();
				//Guardar log de error log
			}
		}		
	}
	
	public void unableUnavailableOffers() throws SQLException{
		// Checkear si hay ofertas que no existen mas
		Dao daoOfertas = DaoFactory.getDao( "Offers", "ar.edu.ubp.das.src.orchestrator" );
		DynaActionForm daf = new DynaActionForm();
		daoOfertas.delete(daf);
//		disableOferta
		
		
	}
}

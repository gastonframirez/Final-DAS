package ar.edu.ubp.das.src.orchestrator.unitTests;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.clients.RestClient;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;


public class UTestRest {
	public static void main(String []args){
		
		System.out.println("Iniciando Cliente Rest");
		
		RestClient client = new RestClient();
		
		List<DynaActionForm> ofertas = client.getOfertas("Token abbb4a0574aab5e145060b12379d88a3", "ofertas", "http://localhost:8080/ComercioRest/rest/api");
		System.out.println(ofertas.size());
		for(DynaActionForm oferta : ofertas) {
			OfferForm offer = (OfferForm) oferta;
			System.out.println(offer.getStatus());
		}
//		System.out.println((String)res[0]);
	}
}

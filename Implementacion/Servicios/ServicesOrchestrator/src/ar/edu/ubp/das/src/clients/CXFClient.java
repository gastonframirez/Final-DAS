package ar.edu.ubp.das.src.clients;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;

public class CXFClient implements WSClient {

	@Override
	public List<DynaActionForm> getOfertas(String authToken, String funcion, String url) {

		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		try {
			Client client = dcf.createClient(url);
			 
			Object[] resSOAP = client.invoke(funcion, authToken);
			List<Object> results = (List<Object>) resSOAP[0];
			
			if(results.size()>0) {
				Method m1 = results.get(0).getClass().getMethod("getStatus"); 
				Method m2 = results.get(0).getClass().getMethod("getErrorMsg");
				Method m3 = results.get(0).getClass().getMethod("getFechaInicio"); 
				Method m4 = results.get(0).getClass().getMethod("getFechaFin"); 
				Method m5 = results.get(0).getClass().getMethod("getUrl"); 
				Method m6 = results.get(0).getClass().getMethod("getImageURL"); 
				Method m7 = results.get(0).getClass().getMethod("getIdOferta"); 
				
				for(Object o : results) {
					OfferForm offer = new OfferForm();
					offer.setStatus((String)m1.invoke(o));
					offer.setErrorMsg((String)m2.invoke(o));
					if(offer.getStatus().equals("200")) {
						offer.setFechaInicio((String)m3.invoke(o));
						offer.setFechaFin((String)m4.invoke(o));
						offer.setUrl((String)m5.invoke(o));
						offer.setImageURL((String)m6.invoke(o));
						offer.setIdOferta((String)m7.invoke(o));
					}
					ofertas.add(offer);
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return ofertas;
	}

	@Override
	public void notificarTransaccion(DynaActionForm transaccion, String authToken, String url, String funcion) throws Exception{
		// TODO Auto-generated method stub
		
	}

}

package ar.edu.ubp.das.src.clients;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.orchestrator.actions.OfertasAction;
import ar.edu.ubp.das.src.orchestrator.beans.OfertaResponseBean;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;

public class Axis2Client implements WSClient {
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
					offer.setStatus((String)((JAXBElement)m1.invoke(o)).getValue());
					offer.setErrorMsg((String)((JAXBElement)m2.invoke(o)).getValue());
					if(offer.getStatus().equals("200")) {
						offer.setFechaInicio((String)((JAXBElement)m3.invoke(o)).getValue());
						offer.setFechaFin((String)((JAXBElement)m4.invoke(o)).getValue());
						offer.setUrl((String)((JAXBElement)m5.invoke(o)).getValue());
						offer.setImageURL((String)((JAXBElement)m6.invoke(o)).getValue());
						offer.setIdOferta((String)((JAXBElement)m7.invoke(o)).getValue());
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

	
}

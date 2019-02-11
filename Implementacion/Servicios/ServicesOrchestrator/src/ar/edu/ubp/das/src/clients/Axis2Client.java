package ar.edu.ubp.das.src.clients;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.jws.WebService;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.orchestrator.beans.ResponseBean;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;
import ar.edu.ubp.das.src.orchestrator.forms.TransactionForm;

public class Axis2Client implements WSClient {
	@Override
	public List<DynaActionForm> getOfertas(String authToken, String url, String funcion) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

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
		Thread.currentThread().setContextClassLoader(classLoader);
		return ofertas;
	}

	@Override
	public String notificarTransaccion(DynaActionForm transaccion, String authToken, String url, String funcion) throws Exception{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		
		String resStatus = "400";
		
		try {
			Client client = dcf.createClient(url);
			 
			
			TransactionForm tranF = new TransactionForm();
			
			tranF.setUserName( transaccion.getItem("nombreCliente"));
			tranF.setUserLastName(transaccion.getItem("apellidoCliente"));
			tranF.setUserEmail(transaccion.getItem("emailCliente"));
			tranF.setUserDni(Integer.valueOf(transaccion.getItem("dniCliente")));
			tranF.setTransactionType(transaccion.getItem("tipoTransaccion"));
			tranF.setFecha(transaccion.getItem("fechaTransaccion"));
			
			if(!transaccion.getItem("idOferta").equals("null"))
				tranF.setOfferID(transaccion.getItem("idOferta"));
			
			if(!transaccion.getItem("modeloProducto").equals("null"))
				tranF.setProductID(transaccion.getItem("modeloProducto"));
			
			if(!transaccion.getItem("precioProducto").equals("null"))
				tranF.setProductPrice(Float.valueOf(transaccion.getItem("precioProducto")));
			
			tranF.setCommision(Float.valueOf(transaccion.getItem("comision")));
			
			Object[] resSOAP = client.invoke(funcion, authToken, tranF.getFecha(), tranF.getUserName(),
											tranF.getUserLastName(), tranF.getUserEmail(), tranF.getUserDni(),
											tranF.getTransactionType(), (tranF.getProductID() != null? tranF.getProductID() : null), (tranF.getOfferID()!=null? Integer.valueOf(tranF.getOfferID()) : null),
											(tranF.getProductPrice()!=null ? tranF.getProductPrice() : null), tranF.getCommision());

			Object results = resSOAP[0];
			
			ResponseBean respuesta = new ResponseBean();
			
			if(results!=null) {
				Method m1 = results.getClass().getMethod("getStatus"); 
				Method m2 = results.getClass().getMethod("getErrorMsg");
				
				respuesta.setStatus((String)((JAXBElement)m1.invoke(results)).getValue());
				respuesta.setErrorMsg((String)((JAXBElement)m2.invoke(results)).getValue());
			}
			
			if(!respuesta.getStatus().equals("200")) {
				System.out.println("error:" + respuesta.getStatus());
				resStatus = respuesta.getStatus();
				throw new RuntimeException(respuesta.getErrorMsg());
			}else {
				resStatus = "200";
			}
			
		}
		catch (Exception e) {
			resStatus="400";
			e.printStackTrace();
		}
		Thread.currentThread().setContextClassLoader(classLoader);
		return resStatus;
	}

	
}

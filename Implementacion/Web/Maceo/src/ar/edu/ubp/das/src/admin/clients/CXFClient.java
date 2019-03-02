package ar.edu.ubp.das.src.admin.clients;

import java.lang.reflect.Method;

import javax.xml.bind.JAXBElement;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.admin.beans.ResponseBean;
import ar.edu.ubp.das.src.productos.forms.MensajeForm;

public class CXFClient implements WSClient {

	@Override
	public Integer testConnection(String authToken, String url, String funcion) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		
		try {
			Client client = dcf.createClient(url);
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		Thread.currentThread().setContextClassLoader(classLoader);
		return 1;
	}

	@Override
	public String notificarMensaje(DynaActionForm mensaje, String authToken, String url, String funcion)
			throws Exception {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		
		String resStatus = "400";
		
		try {
			Client client = dcf.createClient(url);
			 
			
			MensajeForm msgForm = new MensajeForm();
			msgForm.setNombreCliente(mensaje.getItem("nombreCliente"));
			msgForm.setApellidoCliente(mensaje.getItem("apellidoCliente"));
			msgForm.setDniCliente(mensaje.getItem("dniCliente"));
			msgForm.setEmailCliente(mensaje.getItem("emailCliente"));
			msgForm.setMensaje(mensaje.getItem("mensaje"));
			msgForm.setNombreProducto(mensaje.getItem("prodModel"));

			
			Object[] resSOAP = client.invoke(funcion, authToken, msgForm.getNombreCliente(),
					msgForm.getApellidoCliente(), msgForm.getEmailCliente(), Integer.parseInt(msgForm.getDniCliente()), msgForm.getNombreProducto(),
					msgForm.getMensaje());

			Object results = resSOAP[0];
			
			ResponseBean respuesta = new ResponseBean();
			
			if(results!=null) {
				Method m1 = results.getClass().getMethod("getStatus"); 
				Method m2 = results.getClass().getMethod("getErrorMsg");
				
				respuesta.setStatus((String)m1.invoke(results));
				respuesta.setErrorMsg((String)m2.invoke(results));
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

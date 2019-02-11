package ar.edu.ubp.das.src.admin.clients;

import java.util.LinkedList;
import java.util.List;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
//import ar.edu.ubp.das.src.beans.ResponseBean;

public class Axis2Client implements WSClient {

	@Override
	public Integer testConnection(String authToken, String url, String funcion) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("En Axis client");
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		try {
			Client client = dcf.createClient(url);
		}
		catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().setContextClassLoader(classLoader);
			System.out.println("Error al crear client");
			return 0;
		}
		Thread.currentThread().setContextClassLoader(classLoader);

		return 1;
	}



	
}

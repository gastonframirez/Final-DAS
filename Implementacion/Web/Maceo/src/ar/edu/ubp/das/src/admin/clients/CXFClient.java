package ar.edu.ubp.das.src.admin.clients;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

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


}

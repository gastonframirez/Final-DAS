package ar.edu.ubp.das.src.orchestrator.unitTests;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


public class UTestCXF {
	public static void main(String []args){
		
		System.out.println("Iniciando Cliente CXF");
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		Client client = dcf.createClient("http://localhost:9090/ApiWSPort?wsdl");
		 
		Object[] res = null;
		try {
			res = client.invoke("getOfertas", "Token abbb4a0574aab5e145060b12379d88a3");
			for(Object o : res) {
				System.out.println(o.toString());
			}
		}
		catch (Exception e) {
			
			// TODO Auto-generated catch block
			System.out.println( e.getMessage() );
			e.printStackTrace();
		}

//		System.out.println((String)res[0]);
	}
}

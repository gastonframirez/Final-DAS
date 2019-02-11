package ar.edu.ubp.das.src.admin.clients;



public class ClientFactory {

	public static WSClient getClient(String clientName, String clientPackage) throws Exception {

		WSClient cliente = WSClient.class.cast(Class.forName(ClientFactory.getClienteClassName(clientName, clientPackage)).newInstance());
	    return cliente;            

	}
	
	private static String getClienteClassName(String clientName, String clientPackage){
            return clientPackage + ".clients."+clientName;

    }
}

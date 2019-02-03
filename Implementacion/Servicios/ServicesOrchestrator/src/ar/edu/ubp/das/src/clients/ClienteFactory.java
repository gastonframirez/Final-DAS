package ar.edu.ubp.das.src.clients;



public class ClienteFactory {

	public static Client getClient(String clienteName, String clientePackage) throws Exception {

		Client cliente = Client.class.cast(Class.forName(ClienteFactory.getClienteClassName(clienteName, clientePackage)).newInstance());
	    return cliente;            

	}
	
	private static String getClienteClassName(String clienteName, String clientePackage){
            return clientePackage + ".clients.Cliente"+clienteName;

    }
}

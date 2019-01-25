package ar.edu.ubp.das.src.comercio.ws;

import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.comercio.daos.MSOfertasDao;
import ar.edu.ubp.das.src.comercio.daos.MSTransaccionDao;

@WebService(targetNamespace = "http://ws.comercio.src.das.ubp.edu.ar/", portName = "TransaccionWSPort", serviceName = "TransaccionWSService")
public class TransaccionWS {

	@WebMethod(operationName = "insertTransaccion", action = "urn:InsertTransaccion")
	public String insertTransaccion(@WebParam(name = "arg0") String fechaTransaccion, @WebParam(name = "arg1") String nombreCliente, 
			@WebParam(name = "arg2") String apellidoCliente, @WebParam(name = "arg3") String emailCliente, 
			@WebParam(name = "arg4") Integer dniCliente, @WebParam(name = "arg5") String tipoTransaccion, 
			@WebParam(name = "arg6") String modeloProducto, @WebParam(name = "arg7") Integer idOferta, 
			@WebParam(name = "arg8") Float precioProducto, @WebParam(name = "arg9") String authToken) {
		
		if(authToken!=null) {
			if(authToken.indexOf("Token")!=-1 && authToken.split(" ").length==2) {
				String token = authToken.split(" ")[1];
	
				DynaActionForm daf = new DynaActionForm();
				daf.setItem("hashToken", token);
				
				try {
					MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
					if(dao.valid(daf)){
						System.out.println("Token valido");

						if(fechaTransaccion == null || nombreCliente == null || apellidoCliente == null || emailCliente == null || 
								dniCliente == null || tipoTransaccion == null || modeloProducto == null || precioProducto == null) {
							return "No se aportaron todos los datos requeridos.";
						}
						
						if(tipoTransaccion.equals("O") && idOferta==null) {
							return "No se indico el id de la oferta.";
						}
						
						System.out.print("Obteniendo datos...\n");
						System.out.print(fechaTransaccion+"\n");
						System.out.print(nombreCliente+"\n");
						System.out.print(apellidoCliente+"\n");
						System.out.print(emailCliente+"\n");
						System.out.print(dniCliente+"\n");
						System.out.print(tipoTransaccion+"\n");
						System.out.print(modeloProducto+"\n");
						System.out.print(idOferta+"\n");
						System.out.print(precioProducto+"\n");
					
						daf.setItem("fechaTransaccion", fechaTransaccion);
						daf.setItem("nombreCliente", nombreCliente);
						daf.setItem("apellidoCliente", apellidoCliente);
						daf.setItem("emailCliente", emailCliente);
						daf.setItem("dniCliente", dniCliente.toString());
						daf.setItem("tipoTransaccion", tipoTransaccion);
						daf.setItem("modeloProducto", modeloProducto);		
						daf.setItem("precioProducto", precioProducto.toString());
						
						if(idOferta != null)
							daf.setItem("idOferta", idOferta.toString());
						
						dao.insert(daf);

									
						return "OK";
						
					}else {
						System.out.println("Token inexistente.");
						return ("Error 401: Token inexistente.");
					}
							
				} catch ( SQLException e ) {
					e.printStackTrace();
		    	    return ("Error 400: Error al obtener datos.");
				}

			}else {
				System.out.println("Token mal formado.");
				return ("Error 401: Token mal formado.");
			}
		}else {
			System.out.println("Token no provisto.");
			return ("Error 401: Token no provisto.");
		}
	}
}

package ar.edu.ubp.das.src.comercio.ws;

import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.comercio.daos.MSTransaccionDao;

@WebService(targetNamespace = "http://ws.comercio.src.das.ubp.edu.ar/", portName = "TransaccionWSPort", serviceName = "TransaccionWSService")
public class TransaccionWS {

	@WebMethod(operationName = "insertTransaccion", action = "urn:InsertTransaccion")
	public String insertTransaccion(@WebParam(name = "arg0") String fechaTransaccion, @WebParam(name = "arg1") String nombreCliente, 
			@WebParam(name = "arg2") String apellidoCliente, @WebParam(name = "arg3") String emailCliente, 
			@WebParam(name = "arg4") Integer dniCliente, @WebParam(name = "arg5") String tipoTransaccion, 
			@WebParam(name = "arg6") String modeloProducto, @WebParam(name = "arg7") Integer idOferta, 
			@WebParam(name = "arg8") Float precioProducto) {
		
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
				
		try {
			
			MSTransaccionDao dao = (MSTransaccionDao)DaoFactory.getDao( "Transaccion", "ar.edu.ubp.das.src.comercio" );
			
			DynaActionForm dynamicForm = new DynaActionForm();

			dynamicForm.setItem("fechaTransaccion", fechaTransaccion);
			dynamicForm.setItem("nombreCliente", nombreCliente);
			dynamicForm.setItem("apellidoCliente", apellidoCliente);
			dynamicForm.setItem("emailCliente", emailCliente);
			dynamicForm.setItem("dniCliente", dniCliente.toString());
			dynamicForm.setItem("tipoTransaccion", tipoTransaccion);
			dynamicForm.setItem("modeloProducto", modeloProducto);		
			dynamicForm.setItem("precioProducto", precioProducto.toString());
			
			if(idOferta != null)
				dynamicForm.setItem("idOferta", idOferta.toString());
			
			dao.insert(dynamicForm);

						
			return "OK";
			
		} catch ( SQLException error ) {
    	    return error.getMessage();
		}
	}
}

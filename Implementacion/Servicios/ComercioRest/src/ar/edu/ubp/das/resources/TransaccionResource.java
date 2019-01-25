package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.comercio.daos.MSTransaccionDao;

@Path("/transacciones")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class TransaccionResource {

	@POST
	public Response insertTransaccion(@Context HttpHeaders headers, @FormParam("fechaTransaccion") String fechaTransaccion, @FormParam("nombreCliente") String nombreCliente, 
			@FormParam("apellidoCliente") String apellidoCliente, @FormParam("emailCliente") String emailCliente, 
			@FormParam("dniCliente") Integer dniCliente, @FormParam("tipoTransaccion") String tipoTransaccion, 
			@FormParam("modeloProducto") String modeloProducto, @FormParam("idOferta") Integer idOferta, 
			@FormParam("precioProducto") Float precioProducto) {
		
		List<String> authToken = headers.getRequestHeader("Authorization");
		
		if(authToken!=null) {
			if(authToken.get(0).indexOf("Token")!=-1 && authToken.get(0).split(" ").length==2) {
				try {
//					System.out.println(authToken.get(0).split(" ")[1]);
					DynaActionForm dynamicForm = new DynaActionForm();
					
					String token = authToken.get(0).split(" ")[1];

					dynamicForm.setItem("hashToken", token);

					MSTransaccionDao dao = (MSTransaccionDao)DaoFactory.getDao( "Transaccion", "ar.edu.ubp.das.src.comercio" );

					if(dao.valid(dynamicForm)){
						System.out.println("Token valido");
						if(fechaTransaccion == null || nombreCliente == null || apellidoCliente == null || emailCliente == null || 
								dniCliente == null || tipoTransaccion == null || modeloProducto == null || precioProducto == null) {
							return Response.status( Response.Status.BAD_REQUEST ).entity( "No se aportaron todos los datos requeridos." ).build();
						}
						
						if(tipoTransaccion.equals("O") && idOferta==null) {
							return Response.status( Response.Status.BAD_REQUEST ).entity( "No se indico el id de la oferta." ).build();
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

									
						return Response.status( Response.Status.OK ).entity("OK").build();
					}else {
						System.out.println("Token inexistente.");
						return Response.status( Response.Status.UNAUTHORIZED ).entity("Token inexistente.").build();
					}
					
					
				} catch ( SQLException error ) {
		    	    return Response.status( Response.Status.BAD_REQUEST ).entity( error.getMessage() ).build();
				}
			}else {
				System.out.println("Token mal formado.");
				return Response.status( Response.Status.UNAUTHORIZED ).entity("Token mal formado.").build();
			}
		}else {
			System.out.println("Token no provisto.");
			return Response.status( Response.Status.UNAUTHORIZED ).entity("Token no provisto.").build();
		}
	
	}
	
}

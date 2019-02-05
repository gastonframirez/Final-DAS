package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.beans.ResponseBean;
import ar.edu.ubp.das.src.comercio.daos.MSOfertasDao;
import ar.edu.ubp.das.src.comercio.daos.MSTransaccionDao;
import ar.edu.ubp.das.src.comercio.forms.OfertaForm;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ApiResource {
	
	@POST
	@Path("/transacciones")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response insertTransaccion(@Context HttpHeaders headers, @FormParam("fechaTransaccion") String fechaTransaccion, @FormParam("nombreCliente") String nombreCliente, 
			@FormParam("apellidoCliente") String apellidoCliente, @FormParam("emailCliente") String emailCliente, 
			@FormParam("dniCliente") Integer dniCliente, @FormParam("tipoTransaccion") String tipoTransaccion, 
			@FormParam("modeloProducto") String modeloProducto, @FormParam("idOferta") Integer idOferta, 
			@FormParam("precioProducto") Float precioProducto, @FormParam("comision") Float comision) {
		
		List<String> authToken = headers.getRequestHeader("Authorization");
		Gson gson = new GsonBuilder().create();
		ResponseBean resp = new ResponseBean();
		System.out.println("EN SERVICIO");
		if(authToken!=null) {
			if(authToken.get(0).indexOf("Token")!=-1 && authToken.get(0).split(" ").length==2) {
				try {
					MSTransaccionDao daoTransacciones = (MSTransaccionDao)DaoFactory.getDao( "Transaccion", "ar.edu.ubp.das.src.comercio" );
					
					String token = authToken.get(0).split(" ")[1];
					
					DynaActionForm daf = new DynaActionForm();
					daf.setItem("hashToken", token);
					
					if(daoTransacciones.valid(daf)){
						System.out.println("Token valido");
						if(fechaTransaccion == null || nombreCliente == null || apellidoCliente == null || emailCliente == null || 
								dniCliente == null || comision == null || (tipoTransaccion.equals("ppOffer") && idOferta == null) || 
								(tipoTransaccion.equals("ppClick") && (modeloProducto == null || precioProducto == null))) {

							resp.setStatus(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()));
							resp.setErrorMsg("No se aportaron todos los datos requeridos.");
							return Response.status( Response.Status.BAD_REQUEST ).entity(gson.toJson(resp)).build();
						}else {
							System.out.print("Obteniendo datos de transaccion...\n");
							
							daf.setItem("fechaTransaccion", fechaTransaccion);
							daf.setItem("nombreCliente", nombreCliente);
							daf.setItem("apellidoCliente", apellidoCliente);
							daf.setItem("emailCliente", emailCliente);
							daf.setItem("dniCliente", dniCliente.toString());
							daf.setItem("tipoTransaccion", tipoTransaccion);
							
							if(modeloProducto!=null) 
								daf.setItem("modeloProducto", modeloProducto);	
							
							if(precioProducto!=null) {
								daf.setItem("precioProducto", precioProducto.toString());
							}
							
							if(idOferta != null)
								daf.setItem("idOferta", idOferta.toString());
							
							
							daf.setItem("comision", comision.toString());
							
							System.out.print(fechaTransaccion+"\n");
							System.out.print(nombreCliente+"\n");
							System.out.print(apellidoCliente+"\n");
							System.out.print(emailCliente+"\n");
							System.out.print(dniCliente+"\n");
							System.out.print(tipoTransaccion+"\n");
							System.out.print(modeloProducto+"\n");
							System.out.print(idOferta+"\n");
							System.out.print(precioProducto+"\n");
							System.out.print(comision+"\n");
		
							
							daoTransacciones.insert(daf);
							
							resp.setStatus("200");
							resp.setErrorMsg("OK");
							return Response.status( Response.Status.OK ).entity(gson.toJson(resp)).build();
							
						}
						
					}else {
						System.out.println("Token inexistente.");
						resp.setStatus(String.valueOf(Response.Status.UNAUTHORIZED.getStatusCode()));
						resp.setErrorMsg("Token inexistente.");
						return Response.status( Response.Status.UNAUTHORIZED ).entity(gson.toJson(resp)).build();
					}
					
					
				} catch ( SQLException error ) {
					resp.setStatus(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()));
					resp.setErrorMsg(error.getMessage());
		    	    return Response.status( Response.Status.BAD_REQUEST ).entity( gson.toJson(resp) ).build();
				}
			}else {
				System.out.println("Token mal formado.");
				resp.setStatus(String.valueOf(Response.Status.UNAUTHORIZED.getStatusCode()));
				resp.setErrorMsg("Token mal formado.");
				return Response.status( Response.Status.UNAUTHORIZED ).entity(gson.toJson(resp)).build();
			}
		}else {
			System.out.println("Token no provisto.");
			resp.setStatus(String.valueOf(Response.Status.UNAUTHORIZED.getStatusCode()));
			resp.setErrorMsg("Token no provisto.");
			return Response.status( Response.Status.UNAUTHORIZED ).entity(gson.toJson(resp)).build();
		}
	
	}
	
	@GET
	@Path("/ofertas")
	public Response getOfertas(@Context HttpHeaders headers) {
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		List<String> authToken = headers.getRequestHeader("Authorization");
		
		if(authToken!=null) {
			if(authToken.get(0).indexOf("Token")!=-1 && authToken.get(0).split(" ").length==2) {
				String token = authToken.get(0).split(" ")[1];
	
				DynaActionForm daf = new DynaActionForm();
				daf.setItem("hashToken", token);
				
				try {
					MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
					if(dao.valid(daf)){

						ofertas = dao.select(daf);

						LinkedList<Map<String,Object>> ofertasForJson = new LinkedList<Map<String,Object>>();
						
						for( DynaActionForm c : ofertas ) {
							ofertasForJson.add(c.getItems());
						}
						
						Gson gson = new GsonBuilder().create();
						
						return Response.status( Response.Status.OK ).entity( gson.toJson(ofertasForJson) ).build();
					}else {
						System.out.println("Token inexistente.");
						return Response.status( Response.Status.UNAUTHORIZED ).entity("Token inexistente.").build();
					}
							
				} catch ( SQLException e ) {
					e.printStackTrace();
		    	    return Response.status( Response.Status.BAD_REQUEST ).entity( "Error al obtener datos" ).build();
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

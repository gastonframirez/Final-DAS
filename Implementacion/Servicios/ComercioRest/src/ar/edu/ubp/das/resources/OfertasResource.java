package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.*;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.comercio.daos.MSOfertasDao;

@Path("/ofertas")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class OfertasResource {

	@GET
	public Response getOfertas(@Context HttpHeaders headers) {
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		List<String> authToken = headers.getRequestHeader("Authorization");
		
		if(authToken!=null) {
			if(authToken.get(0).indexOf("Token")!=-1 && authToken.get(0).split(" ").length==2) {
				String token = authToken.get(0).split(" ")[1];
//				System.out.println("Token" + token);
	
				DynaActionForm daf = new DynaActionForm();
				daf.setItem("hashToken", token);
				
				try {
					MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
					if(dao.valid(daf)){
						System.out.println("Token valido");
						
						
						
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

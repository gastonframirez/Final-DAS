package ar.edu.ubp.das.resources;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	public Response getOfertas() {
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		try {
			
			MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
			
			DynaActionForm daf = new DynaActionForm();

			ofertas = dao.select(daf);

			LinkedList<Map<String,Object>> ofertasForJson = new LinkedList<Map<String,Object>>();
			
			for( DynaActionForm c : ofertas ) {
				ofertasForJson.add(c.getItems());
			}
			
			Gson gson = new GsonBuilder().create();
			
			return Response.status( Response.Status.OK ).entity( gson.toJson(ofertasForJson) ).build();
			
		} catch ( SQLException error ) {
    	    return Response.status( Response.Status.BAD_REQUEST ).entity( "Error al obtener datos" ).build();
		}
	}
	
}

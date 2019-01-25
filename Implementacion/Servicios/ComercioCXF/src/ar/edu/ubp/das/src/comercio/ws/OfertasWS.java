package ar.edu.ubp.das.src.comercio.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.google.gson.*;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.comercio.daos.MSOfertasDao;

@WebService(targetNamespace = "http://ws.comercio.src.das.ubp.edu.ar/", portName = "OfertasWSPort", serviceName = "OfertasWSService")
public class OfertasWS {

	@WebMethod(operationName = "getOfertas", action = "urn:GetOfertas")
	public String getOfertas(@WebParam(name = "arg0") String authToken) {
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		if(authToken!=null) {
			if(authToken.indexOf("Token")!=-1 && authToken.split(" ").length==2) {
				String token = authToken.split(" ")[1];
	
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
						return gson.toJson(ofertasForJson);
						
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

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
	public String getOfertas() {
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		try {

			MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );

			DynaActionForm daf = new DynaActionForm();

			ofertas = dao.select(daf);
			System.out.print("ACA4");
			LinkedList<Map<String,Object>> ofertasForJson = new LinkedList<Map<String,Object>>();
			
			for( DynaActionForm c : ofertas ) {
				ofertasForJson.add(c.getItems());
			}
			
			Gson gson = new GsonBuilder().create();
			return gson.toJson(ofertasForJson);
			
		} catch ( SQLException error ) {
    	    return error.getMessage();
		}
	}
	
}

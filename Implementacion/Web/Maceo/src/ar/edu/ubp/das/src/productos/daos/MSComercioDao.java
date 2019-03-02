package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.CategoriasForm;
import ar.edu.ubp.das.src.productos.forms.ServicioForm;

public class MSComercioDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		this.connect();
		
		this.setProcedure("dbo.getServicesByType(?, ?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, form.getItem("idComercio"));
		this.setParameter(2, form.getItem("idTipoServ"));
		
    	List<DynaActionForm> servicios = new LinkedList<DynaActionForm>();
    	
    	ServicioForm servicio;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	servicio = new ServicioForm();
        	servicio.setServiceURL(result.getString("service_url"));
        	servicio.setFuncion(result.getString("funcion"));
        	servicio.setAuthToken(result.getString("auth_token"));
        	servicio.setJavaClass(result.getString("javaClass"));
        	servicios.add(servicio);
        }
        
		this.disconnect();
		
		return servicios;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

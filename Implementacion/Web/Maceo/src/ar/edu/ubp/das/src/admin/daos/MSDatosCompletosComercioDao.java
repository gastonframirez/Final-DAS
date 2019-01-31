package ar.edu.ubp.das.src.admin.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.ComercioForm;

public class MSDatosCompletosComercioDao extends DaoImpl {

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
		this.connect();
		
		this.setProcedure("dbo.getComerciosExtra(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    	List<DynaActionForm> comercios = new LinkedList<DynaActionForm>();
    	
    	ComercioForm comercio;
    	
    	if(form.getItem("idComercio")!=null) {
    		this.setParameter(1, form.getItem("idComercio"));
    	}else {
    		this.setNull(1, Types.INTEGER);
    	}
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	comercio = new ComercioForm();
        	comercio.setIdComercio(result.getInt("id_comercio"));
        	comercio.setNombre(result.getString("nombre"));
        	comercio.setLogoURL(result.getString("logo_url"));
        	comercio.setCantOffers(result.getInt("q_offers"));
        	comercio.setTotComisiones(result.getFloat("tot_comm"));
        	comercio.setServiceStatus(result.getBoolean("serv_status"));
        	comercio.setHabilitado(result.getBoolean("habilitado"));

        	comercios.add(comercio);
        }
        
		this.disconnect();
		
		return comercios;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

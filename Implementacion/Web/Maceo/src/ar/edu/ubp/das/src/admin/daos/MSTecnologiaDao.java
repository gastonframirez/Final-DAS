package ar.edu.ubp.das.src.admin.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.TecnologiaForm;

public class MSTecnologiaDao extends DaoImpl{

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
		
		this.setProcedure("dbo.getTecnologias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    	List<DynaActionForm> tecnologias = new LinkedList<DynaActionForm>();
    	
    	TecnologiaForm tecnologia;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	tecnologia = new TecnologiaForm();
        	tecnologia.setId(result.getString("id_tecnologia"));
        	tecnologia.setNombre(result.getString("nombre"));
        	tecnologia.setJavaClass(result.getString("javaClass"));
        	tecnologias.add(tecnologia);
        }
        
		this.disconnect();
		
		return tecnologias;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

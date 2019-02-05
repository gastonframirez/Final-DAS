package ar.edu.ubp.das.src.admin.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.EstadisticaForm;

public class MSGraficosDashboardDao extends DaoImpl {

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
		this.setProcedure("dbo.actionsByType(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
    	List<DynaActionForm> datosGrafico = new LinkedList<DynaActionForm>();
    	
    	EstadisticaForm estadistica;
    	
    	if(form.getItem("idComercio")!=null) {
    		this.setParameter(1, form.getItem("idComercio"));
    	}else {
    		this.setNull(1, Types.INTEGER);
    	}
    	ResultSet result = this.getStatement().executeQuery();
    	
    	List<String> tipos = new LinkedList<String>();
    	
        while(result.next()) {
        	estadistica = new EstadisticaForm();
        	estadistica.setNombre(result.getString("nombre"));
        	estadistica.setValor(result.getString("stats"));
        	if(!tipos.contains(result.getString("nombre"))) {
        		tipos.add(result.getString("nombre"));
        	}
        	datosGrafico.add(estadistica);
        }
       
        this.connect();
		this.setProcedure("dbo.getTypes", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
    	
    	result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	if(!tipos.contains(result.getString("nombre"))) {
        		tipos.add(result.getString("nombre"));
        		estadistica = new EstadisticaForm();
            	estadistica.setNombre(result.getString("nombre"));
            	estadistica.setValor("0");
            	datosGrafico.add(estadistica);
        	}
        }
       
		this.disconnect();
		
		return datosGrafico;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

package ar.edu.ubp.das.src.admin.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.ComercioForm;
import ar.edu.ubp.das.src.admin.forms.EstadisticaForm;

public class MSEstadisticasDashboardDao extends DaoImpl {

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
		this.setProcedure("dbo.monthlyTransactions(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		Date currMonth= new Date(System.currentTimeMillis());
		
		this.setParameter(1,currMonth);
		
    	List<DynaActionForm> estadisticas = new LinkedList<DynaActionForm>();
    	
    	EstadisticaForm estadistica;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        if(result.next()) {
        	estadistica = new EstadisticaForm();
        	estadistica.setNombre("transactionTotal");
        	estadistica.setValor(result.getString("stats"));
        	estadisticas.add(estadistica);
        }
        
        this.setProcedure("dbo.activeOffers", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    	    	
    	result = this.getStatement().executeQuery();
  
        if(result.next()) {
        	estadistica = new EstadisticaForm();
        	estadistica.setNombre("activeOffers");
        	estadistica.setValor(result.getString("stats"));
        	estadisticas.add(estadistica);
        }
		
        
        
		this.setProcedure("dbo.newMonthlyUsers(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		this.setParameter(1,currMonth);
    	    	
    	result = this.getStatement().executeQuery();
  
        if(result.next()) {
        	estadistica = new EstadisticaForm();
        	estadistica.setNombre("newUsers");
        	estadistica.setValor(result.getString("stats"));
        	estadisticas.add(estadistica);
        }
        
		this.disconnect();
		
		return estadisticas;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

package ar.edu.ubp.das.src.admin.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.ComisionPorMesForm;
import ar.edu.ubp.das.src.admin.forms.HistoricoComisionForm;

public class MSGraficosComercioDao extends DaoImpl {

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
		
    	List<DynaActionForm> datosGrafico = new LinkedList<DynaActionForm>();
    	
    	if(form.getItem("idComercio")!=null) {
    		
    		this.setProcedure("dbo.comissionsEvolution(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		
    		HistoricoComisionForm comisionMes = new HistoricoComisionForm();
    		
    		this.setParameter(1, form.getItem("idComercio"));
    		
    		ResultSet result = this.getStatement().executeQuery();
    		  
    		LinkedHashMap<String, Float> valuesOffer = new LinkedHashMap<String, Float>();
    		LinkedHashMap<String, Float> valuesProduct = new LinkedHashMap<String, Float>();
    		LinkedHashMap<String, Float> valuesTotal = new LinkedHashMap<String, Float>();
    		
    		Float totProd = (float) 0.0;
    		Float totOff = (float) 0.0;
    		
            while(result.next()) {
//            	System.out.println(result.getString("year_transaction"));
//            	System.out.println(result.getString("month_transaction"));
            	comisionMes = new HistoricoComisionForm();
            	if(result.getInt("tipo")==1) {
            		totProd+=result.getFloat("month_total");
            	}else if(result.getInt("tipo")==2){
            		totOff+=result.getFloat("month_total");
            	}
            	
            	valuesOffer.put(result.getString("month_transaction") + "/" + result.getString("year_transaction"), totOff);
            	valuesProduct.put(result.getString("month_transaction") + "/" + result.getString("year_transaction"), totProd);
            	
            	valuesTotal.put(result.getString("month_transaction") + "/" + result.getString("year_transaction"), totOff+totProd);
            }
            comisionMes.setValuesOffer(valuesOffer);
            comisionMes.setValuesProduct(valuesProduct);
            comisionMes.setValuesTotal(valuesTotal);
//            System.out.println(valuesTotal);
            datosGrafico.add(comisionMes);
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

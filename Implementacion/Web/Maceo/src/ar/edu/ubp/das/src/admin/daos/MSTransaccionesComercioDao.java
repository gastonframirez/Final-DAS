package ar.edu.ubp.das.src.admin.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.TransaccionForm;

public class MSTransaccionesComercioDao extends DaoImpl {

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
		List<DynaActionForm> transacciones = new LinkedList<DynaActionForm>();
		
		if(form.getItem("idComercio")!=null) {
	        this.setProcedure("dbo.monthlyTransactionsList(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			Date currDate= new Date(System.currentTimeMillis());
			
			this.setParameter(1, currDate);
	    	this.setParameter(2, form.getItem("idComercio"));
	    	
	    	TransaccionForm transaccion;
	    	
	    	ResultSet result = this.getStatement().executeQuery();
	  
	        while(result.next()) {
	        	transaccion = new TransaccionForm();
	        	transaccion.setTransaccionID(result.getInt("id_transaccion"));
	        	transaccion.setFecha(result.getString("fecha"));
	        	if(result.getString("id_oferta") != null) {
	        		transaccion.setOfertaID(result.getInt("id_oferta"));
	        	}
	        	transaccion.setPending(result.getBoolean("pending"));
	        	transaccion.setTransNombre(result.getString("nombre_trans"));
	        	transaccion.setValor(result.getFloat("valor"));
	        	transaccion.setProdNombre(result.getString("nombre_prod"));
	        	transaccion.setProdPrecio(result.getFloat("precio"));
	        	transacciones.add(transaccion);
	        	System.out.println(transaccion.getTransNombre());
	        }

		}
		this.disconnect();
		
		return transacciones;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
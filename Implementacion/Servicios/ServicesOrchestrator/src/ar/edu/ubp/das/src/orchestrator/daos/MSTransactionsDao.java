package ar.edu.ubp.das.src.orchestrator.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.sql.Types;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSTransactionsDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		DynaActionForm form = new DynaActionForm();
		form.setItem("idTransaccion", result.getString("id_transaccion"));
		form.setItem("fechaTransaccion", result.getString("fecha"));
    	form.setItem("nombreCliente", result.getString("userName"));
    	form.setItem("apellidoCliente", result.getString("apellido"));
    	form.setItem("dniCliente", result.getString("dni"));
    	form.setItem("emailCliente", result.getString("email"));
    	form.setItem("tipoTransaccion", result.getString("nombre_transaction"));
    	form.setItem("modeloProducto", result.getString("modelo_producto"));
    	form.setItem("precioProducto", result.getString("precio_producto"));
    	form.setItem("idOferta", result.getString("id_oferta_comercio"));
    	form.setItem("comision", result.getString("commission"));
    	
		return form;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

		try {
        	this.connectWAutoFalse();
        	
        	this.setProcedure("dbo.releasePendingTransaction(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		this.setParameter(1, form.getItem("idTransaccion"));
    		
    		this.getStatement().execute();		
     	
			this.commit();
			
		} catch (SQLException e) {
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
		this.connect();
		
		this.setProcedure("dbo.listPendingTransaction(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.setParameter(1, form.getItem("idComercio"));
		
        List<DynaActionForm> transacciones = this.executeQuery();

        this.disconnect();
		
		return transacciones;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.validateToken(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String token = form.getItem("hashToken");
		
		this.setParameter(1, token);
		
		ResultSet result = this.getStatement().executeQuery();
		
		if(result.next()) {
			return true;
		}
		else {
			return false;
		}
	}

}

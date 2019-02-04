package ar.edu.ubp.das.src.comercio.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.sql.Types;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSTransaccionDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

		try {
        	this.connectWAutoFalse();

        	System.out.println("Guardando transaccion..");
    		this.setProcedure("dbo.saveTransaccion(?,?,?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("fechaTransaccion"));
    		this.setParameter(2, form.getItem("nombreCliente"));
    		this.setParameter(3, form.getItem("apellidoCliente"));
    		this.setParameter(4, form.getItem("emailCliente"));
    		this.setParameter(5, form.getItem("dniCliente"));
    		this.setParameter(6, form.getItem("tipoTransaccion"));

    		if(form.getItem("modeloProducto")!=null) 
    			this.setParameter(7, form.getItem("modeloProducto"));
    		else 
    			this.setNull(7, Types.VARCHAR);
    		
    		if(form.getItem("precioProducto")!=null) 
    			this.setParameter(8, form.getItem("precioProducto"));
    		else 
    			this.setNull(8, Types.FLOAT);
    		
    		
    		if(form.getItem("idOferta") != null)
    			this.setParameter(9, form.getItem("idOferta"));
    		else
    			this.setNull(9, Types.SMALLINT);
    		
    		this.setParameter(10, form.getItem("comision"));
    		
    		System.out.println("Parametros seteados. Ejecutando statement..");
    		
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
		return null;
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

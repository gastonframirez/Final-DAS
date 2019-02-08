package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.security.jbcrypt.BCrypt;

public class MSTransaccionDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		try {
        	this.connectWAutoFalse();

    		this.setProcedure("dbo.saveTransaccion(?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("fecha"));
    		if(form.getItem("idProducto")!=null) {
    			this.setParameter(2, form.getItem("idProducto"));
    		}else {
    			this.setNull(2, Types.INTEGER);
    		}
    		this.setParameter(3, form.getItem("idTipo"));
    		this.setParameter(4, form.getItem("idComercio"));  	
    		this.setParameter(5, form.getItem("idUsuario"));
    		if(form.getItem("idOferta")!=null) {
    			this.setParameter(6, form.getItem("idOferta"));
    		}else {
    			this.setNull(6, Types.INTEGER);
    		}
    		this.setParameter(7, 1);
    		
    		this.getStatement().execute();		
     	
			this.commit();
			
		} catch (SQLException e) {
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
		this.disconnect();

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
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

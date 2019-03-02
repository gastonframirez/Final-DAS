package ar.edu.ubp.das.src.comercio.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.comercio.forms.MensajeForm;

public class MSMensajeDao extends DaoImpl {

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

        	this.setProcedure("dbo.saveMensaje(?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("nombreCliente"));
    		this.setParameter(2, form.getItem("apellidoCliente"));
    		this.setParameter(3, form.getItem("emailCliente"));
    		this.setParameter(4, form.getItem("dniCliente"));
    		this.setParameter(5, form.getItem("message"));
    		this.setParameter(6, form.getItem("modeloProducto"));

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
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
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

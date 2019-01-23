package ar.edu.ubp.das.src.users.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.OfertasForm;

public class MSUserDao extends DaoImpl {

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

    		this.setProcedure("dbo.registerUser(?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("nombreUsuario"));
    		this.setParameter(2, form.getItem("nombre"));
    		this.setParameter(3, form.getItem("apellido"));
    		this.setParameter(4, form.getItem("email"));
    		
    		//ENCRIPTAR PASS ACA
    		String password = form.getItem("password");
    		
    		this.setParameter(5, password);
    		this.setParameter(6, form.getItem("dni"));

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
		return false;
	}

}

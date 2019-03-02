package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

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

        	this.setProcedure("dbo.saveMensaje(?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("idUsuario"));
    		this.setParameter(2, form.getItem("idComercio"));
    		this.setParameter(3, form.getItem("mensaje"));
    		this.setParameter(4, form.getItem("prodModel"));

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
		try {
        	this.connectWAutoFalse();

        	this.setProcedure("dbo.disableMesage(?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("idUsuario"));
    		this.setParameter(2, form.getItem("idComercio"));
    		this.setParameter(3, form.getItem("mensaje"));
    		this.setParameter(4, form.getItem("prodModel"));

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

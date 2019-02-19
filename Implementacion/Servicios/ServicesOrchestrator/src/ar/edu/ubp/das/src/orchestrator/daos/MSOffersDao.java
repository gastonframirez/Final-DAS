package ar.edu.ubp.das.src.orchestrator.daos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSOffersDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// Guardar ofertas nuevas
//		System.out.println("EN DAO");
//		System.out.println(form.getItem("idComercio"));
		System.out.println(form);
		
		try {
        	this.connectWAutoFalse();

//        	Guardo la oferta
    		this.setProcedure("dbo.saveOferta(?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		
    		this.setParameter(1, form.getItem("idComercio"));
    		this.setParameter(2, form.getItem("imageURL"));

    		this.setParameter(3, form.getItem("fechaInicio"));
    		this.setParameter(4, form.getItem("fechaFin"));  	
    		this.setParameter(5, form.getItem("ofertaURL"));
    		this.setParameter(6, form.getItem("idOferta"));

    		this.getStatement().execute();		
    		this.getStatement().close();
    		
			this.commit();
			System.out.println("Oferta guardada");
		} catch (SQLException e) {
			e.printStackTrace();
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
		
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// Borrar (deshabilitar) si no esta mas..
		try {
        	this.connectWAutoFalse();

//        	Guardo la oferta
    		this.setProcedure("dbo.disableOfertas", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.getStatement().execute();		
    		this.getStatement().close();
    		
			this.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
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

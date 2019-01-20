package ar.edu.ubp.das.src.comercio.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSOfertasDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		DynaActionForm form = new DynaActionForm();
        
		form.setItem("fechaInicio", result.getString("fecha_inicio"));
    	form.setItem("fechaFin", result.getString("fecha_fin"));
    	form.setItem("precioOferta",  String.valueOf(result.getFloat("precio_oferta")));
    	form.setItem("modeloProducto", result.getString("modelo"));
        
        return form;
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

		this.setProcedure("dbo.getOfertas", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		List<DynaActionForm> ofertas = this.executeQuery();

		this.disconnect();
		
		return ofertas;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
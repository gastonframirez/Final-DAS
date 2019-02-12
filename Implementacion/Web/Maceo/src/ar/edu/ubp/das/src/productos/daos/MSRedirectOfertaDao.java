package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.OfertasForm;
import ar.edu.ubp.das.src.productos.forms.ProductoForm;
import ar.edu.ubp.das.src.productos.forms.RedirectOfertaForm;
import ar.edu.ubp.das.src.productos.forms.RedirectProductoForm;

public class MSRedirectOfertaDao extends DaoImpl {

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
				
		this.setProcedure("dbo.getOferta(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, Integer.valueOf(form.getItem("idOferta")));
		this.setParameter(2, Integer.valueOf(form.getItem("idComercio")));
		
        ResultSet result = this.getStatement().executeQuery();
		List<DynaActionForm>  ofertas = new LinkedList<DynaActionForm>();

    	RedirectOfertaForm oferta;
    	
        if(result.next()) {
        	oferta = new RedirectOfertaForm();
        	oferta.setComercioNombre(result.getString("nombre_publico"));
        	oferta.setLogoComercio(result.getString("logo_url"));
        	oferta.setUrl(result.getString("url_oferta"));
        	ofertas.add(oferta);

            result.next();
        }
        
		this.disconnect();
		
		return ofertas;
		
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

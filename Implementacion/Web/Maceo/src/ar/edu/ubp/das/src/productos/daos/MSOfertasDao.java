package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.OfertasForm;

public class MSOfertasDao extends DaoImpl {

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
		// TODO Auto-generated method stub
		
		this.connect();
		
		this.setProcedure("dbo.getOfertas", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    	List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
    	
    	OfertasForm oferta;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	oferta = new OfertasForm();
        	oferta.setIdOferta(result.getInt("id_oferta"));
        	oferta.setOfertaImageURL(result.getString("oferta_img_url"));
        	oferta.setFechaFin(result.getString("fecha_fin"));
        	oferta.setFechaInicio(result.getString("fecha_inicio"));
        	oferta.setOfertaURL(result.getString("url_oferta"));
        	oferta.setPrecioOferta(result.getFloat("precio_oferta"));
        	oferta.setPrecioNormal(result.getFloat("precio_normal"));
        	oferta.setIdProducto(result.getInt("id_producto"));
        	oferta.setIdComercio(result.getInt("id_comercio"));
        	oferta.setLogoComercioURL(result.getString("comercio_logo_url"));
        	oferta.setNombreProducto(result.getString("nombre"));
        		
        	ofertas.add(oferta);
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

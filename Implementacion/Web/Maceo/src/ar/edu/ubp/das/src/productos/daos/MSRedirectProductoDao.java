package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.ProductoForm;
import ar.edu.ubp.das.src.productos.forms.RedirectProductoForm;

public class MSRedirectProductoDao extends DaoImpl {

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

		
		List<DynaActionForm>  productos = new LinkedList<DynaActionForm>();
    	
    	RedirectProductoForm producto;


		this.connect();
		
		this.setProcedure("dbo.getProducto(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		this.setParameter(1, String.valueOf(form.getItem("idProducto")));
		this.setParameter(2, String.valueOf(form.getItem("idComercio")));
		
        ResultSet result = this.getStatement().executeQuery();
    	
        if(result.next()) {
        	producto = new RedirectProductoForm();
        	producto.setModelo(result.getString("modelo_producto"));
        	producto.setNombre(result.getString("nombre"));
        	producto.setImagenURL(result.getString("image_url"));
        	producto.setPrecio(result.getFloat("precio"));
        	producto.setProductoURL(result.getString("url_producto"));
        	producto.setLogoComercio(result.getString("logo_url"));
        	producto.setComercioNombre(result.getString("nombre_publico"));
        	
            productos.add(producto);

            result.next();
        }
        
		this.disconnect();
		
		return productos;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

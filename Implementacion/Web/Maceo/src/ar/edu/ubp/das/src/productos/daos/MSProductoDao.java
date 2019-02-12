package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.ProductoForm;

public class MSProductoDao extends DaoImpl {

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
		
				
		List<DynaActionForm>  productos = new LinkedList<DynaActionForm>();
    	LinkedList<ProductoForm> productosAlternativos;
    	ProductoForm producto;

    	ProductoForm productoAux;
    	String modelo;
		
		this.connect();
		
		this.setProcedure("dbo.getProductos(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if(form.getItem("id_categoria").isEmpty()) {
        	this.setNull(1, Types.TINYINT);
        }
        else {
        	this.setParameter(1, String.valueOf(form.getItem("id_categoria")));
        }
        
        ResultSet result = this.getStatement().executeQuery();
    	result.next();
    	
        while(result.getRow() > 0) {
        	producto = new ProductoForm();
        	producto.setIdProducto(result.getInt("id_producto"));
        	producto.setModelo(result.getString("modelo"));
        	producto.setNombre(result.getString("nombre"));
        	producto.setImagenURL(result.getString("image_url"));
        	producto.setPrecio(result.getFloat("precio"));
        	producto.setProductoURL(result.getString("url_producto"));
        	producto.setLogoComercio(result.getString("logo_url"));
        	producto.setIdComercio(result.getInt("id_comercio"));
        	productosAlternativos = new LinkedList<ProductoForm>();

        	modelo = result.getString("modelo");
        	result.next();
        	boolean alternative = false;
        	while(result.getRow() > 0 && modelo.equals(result.getString("modelo"))) {
        		productoAux = new ProductoForm();
        		productoAux.setIdProducto(result.getInt("id_producto"));
        		productoAux.setModelo(result.getString("modelo"));
        		productoAux.setNombre(result.getString("nombre"));
        		productoAux.setImagenURL(result.getString("image_url"));
        		productoAux.setPrecio(result.getFloat("precio"));
        		productoAux.setProductoURL(result.getString("url_producto"));
        		productoAux.setLogoComercio(result.getString("logo_url"));
        		productoAux.setIdComercio(result.getInt("id_comercio"));
        		
            	productosAlternativos.add(productoAux);
            	alternative = true;
        		result.next();
            }
            producto.setProductosAlternativos(productosAlternativos);
            productos.add(producto);
//            if(alternative)
//            result.next();
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

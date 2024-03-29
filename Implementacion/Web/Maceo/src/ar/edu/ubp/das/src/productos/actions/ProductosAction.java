package ar.edu.ubp.das.src.productos.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;


public class ProductosAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {

		form.setItem("id_categoria", String.valueOf(request.getParameter("idCategoria")));
	
		Dao daoCategorias = DaoFactory.getDao( "Categorias", "productos" );
		request.setAttribute("categorias", daoCategorias.select(form));
		
		request.setAttribute("catID", request.getParameter("idCategoria"));
		
		Dao daoProductos = DaoFactory.getDao( "Producto", "productos" );
		request.setAttribute("productos", daoProductos.select(form));
		
		return mapping.getForwardByName("success");
	}


}


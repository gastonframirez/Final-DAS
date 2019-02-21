package ar.edu.ubp.das.src.productos.actions;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;


public class BuscarAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		Dao daoCategorias = DaoFactory.getDao( "Categorias", "productos" );
		request.setAttribute("categorias", daoCategorias.select(form));
		
		request.setAttribute("catID", -1);
		
		if(request.getParameter("searchStr")!=null) {
			String strBusqueda="";
			for(String aux: request.getParameter("searchStr").split(" ")) {
				strBusqueda+=aux+"%";
			}
			form.setItem("search", strBusqueda);
			request.setAttribute("searchStr", request.getParameter("searchStr"));
			Dao daoProductos = DaoFactory.getDao( "Producto", "productos" );
			request.setAttribute("productos", daoProductos.select(form));
		}
		
		
		
		return mapping.getForwardByName("success");
	}


}


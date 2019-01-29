package ar.edu.ubp.das.src.admin.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;

public class ShowAddCategoriaAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		if(request.getParameter("idCategoria")!=null) {
			Dao daoCategorias = DaoFactory.getDao( "Categoria", "admin" );
			
			form.setItem("idCategoria", request.getParameter("idCategoria"));
			
			request.setAttribute("categoria", daoCategorias.select(form).get(0));
		}
		return mapping.getForwardByName("success");
		
	}

}

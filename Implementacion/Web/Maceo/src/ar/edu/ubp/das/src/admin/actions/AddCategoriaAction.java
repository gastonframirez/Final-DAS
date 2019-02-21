package ar.edu.ubp.das.src.admin.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.collections.List;
import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;

public class AddCategoriaAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		Dao daoCategoria = DaoFactory.getDao( "Categoria", "admin" );
		
		form.setItem("spanish", request.getParameter("spanish"));
		form.setItem("english", request.getParameter("english"));
		form.setItem("imageURL", request.getParameter("imageURL"));
		try {
			if(request.getParameter("idCategoria")!=null) {
				form.setItem("idCategoria", request.getParameter("idCategoria"));
				form.setItem("enabled", request.getParameter("enabled"));

				daoCategoria.update(form);
			}else {
				daoCategoria.insert(form);
			}
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
			DynaActionForm formLogs = new DynaActionForm();
			Dao daoLogs = DaoFactory.getDao( "Log", "admin" );
			formLogs.setItem("logStr", "Error al intentar agregrar una categoria.");
			daoLogs.insert(formLogs);
			return mapping.getForwardByName("error");
		}
		
		
		
		
		
		
		
		return mapping.getForwardByName("success");
	}

}

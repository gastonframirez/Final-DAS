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

public class ToggleCategoriaAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		try {
		form.setItem("idCategoria", request.getParameter("idCategoria"));
		if(request.getParameter("enabled")!=null) {
			form.setItem("habilitada", "1");
		}else {
			form.setItem("habilitada", "0");
		}
		Dao daoCategoria = DaoFactory.getDao( "Categoria", "admin" );
		
		daoCategoria.delete(form);
		
		}catch(SQLException ex) {
			DynaActionForm formLogs = new DynaActionForm();
			Dao daoLogs = DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.admin" );
			formLogs.setItem("logStr", "Error al intentar deshabilitar la categoria ID:"+request.getParameter("idCategoria"));
			daoLogs.insert(formLogs);
		}
		return mapping.getForwardByName("success");
	}

}

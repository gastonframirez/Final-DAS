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

public class ToggleComercioAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		form.setItem("idComercio", request.getParameter("idComercio"));
		if(request.getParameter("enabled")!=null) {
			form.setItem("habilitado", "1");
		}else {
			form.setItem("habilitado", "0");
		}
		Dao daoComercio = DaoFactory.getDao( "Comercio", "admin" );
		
		daoComercio.delete(form);
		
		return mapping.getForwardByName("success");
	}

}

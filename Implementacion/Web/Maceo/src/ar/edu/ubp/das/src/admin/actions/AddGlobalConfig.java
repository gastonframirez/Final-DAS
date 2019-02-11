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

public class AddGlobalConfig implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		Dao daoConfig = DaoFactory.getDao( "Config", "admin" );
		System.out.println(request.getParameter("regex"));
		String regex = request.getParameter("regex");
		String words = request.getParameter("words");
		String cssClasses = request.getParameter("cssClass");
		
		try {
			if(!regex.isEmpty()) {
				for(String rgx : regex.split("\\n")) {
					form = new DynaActionForm();
					form.setItem("tipo", "1");
					form.setItem("word", rgx);
					daoConfig.insert(form);
				}
			}
			if(!words.isEmpty()) {
				for(String wrd : words.split("\\n")) {
					form = new DynaActionForm();
					form.setItem("tipo", "2");
					form.setItem("word", wrd);
					daoConfig.insert(form);
				}
			}
			if(!cssClasses.isEmpty()) {
				for(String css : cssClasses.split("\\n")) {
					form = new DynaActionForm();
					form.setItem("tipo", "3");
					form.setItem("word", css);
					daoConfig.insert(form);
				}
			}
			
			daoConfig.delete(form);
			
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
			DynaActionForm formLogs = new DynaActionForm();
			Dao daoLogs = DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.admin" );
			formLogs.setItem("logStr", "Error al intentar guardar las configuraciones.");
			daoLogs.insert(formLogs);
			return mapping.getForwardByName("error");
		}
		return mapping.getForwardByName("success");
	}

}

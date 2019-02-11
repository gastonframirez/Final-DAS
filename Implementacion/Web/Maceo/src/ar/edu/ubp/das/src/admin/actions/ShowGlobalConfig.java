package ar.edu.ubp.das.src.admin.actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.admin.forms.ConfigsForm;

public class ShowGlobalConfig implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		Dao daoConfig = DaoFactory.getDao( "Config", "admin" );
		List<DynaActionForm> configsList = daoConfig.select(form);
		ConfigsForm configs = (ConfigsForm)configsList.get(0);
		
		request.setAttribute("regex", configs.getRegex());
		request.setAttribute("words", configs.getWords());
		request.setAttribute("cssClasses", configs.getCssClasses());
    	
		return mapping.getForwardByName("success");
	}

}

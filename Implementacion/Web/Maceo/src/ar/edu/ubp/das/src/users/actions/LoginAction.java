package ar.edu.ubp.das.src.users.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.users.forms.UserForm;

public class LoginAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		Dao daoUser = DaoFactory.getDao( "User", "users" );

		//Chequear que vengan todos los parametros
		form.setItem("nombreUsuario", request.getParameter("username"));
		form.setItem("password", request.getParameter("password"));
	
//		daoUser.valid(form);
		UserForm user = (UserForm) daoUser.valid(form);
		if(user!=null) {
//			response.set
			HttpSession session = request.getSession(true);
			session.setAttribute("userData", user);
			return mapping.getForwardByName("success");
		}else {
			return mapping.getForwardByName("error");
		}
		
	}

}

package ar.edu.ubp.das.src.users.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
		UserForm user = (UserForm) daoUser.valid(form);
		if(user!=null) {
			request.getSession(true).setAttribute("userData", user);
			//implementar bloqueado
			if(user.isAdmin()) {
				//LLevar a Admin dashboard
				request.getSession(true).setAttribute("isAdmin", true);
				request.setAttribute("loginValidation", "a");
//				return mapping.getForwardByName("admin");
			}else{
				request.setAttribute("loginValidation", "u");
//				return mapping.getForwardByName("success");
			}
			
		}else {
			request.setAttribute("loginValidation", "w");
			
		}
		return mapping.getForwardByName("success");
	}

}

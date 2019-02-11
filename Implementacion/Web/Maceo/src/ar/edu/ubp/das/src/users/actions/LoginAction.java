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
			
			//implementar bloqueado
			if(user.isAdmin()) {
				request.getSession(true).setAttribute("isAdmin", true);
				
				if(user.getIsBlocked()) {
					request.setAttribute("loginValidation", "b");
					response.setStatus(400);
				}else {
					request.setAttribute("loginValidation", "a");
					request.getSession(true).setAttribute("userData", user);
				}
			}else{
				request.setAttribute("loginValidation", "u");
				request.getSession(true).setAttribute("userData", user);
			}
			
			
		}else {
			request.setAttribute("loginValidation", "w");
			response.setStatus(400);
		}
		
		return mapping.getForwardByName("success");
	}

}

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
import ar.edu.ubp.das.security.jbcrypt.BCrypt;
import ar.edu.ubp.das.src.users.forms.UserForm;



public class RegistrarAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		Dao daoUser = DaoFactory.getDao( "User", "users" );
		
		//Chequear que vengan todos los parametros
		form.setItem("nombreUsuario", request.getParameter("username"));
		form.setItem("nombre", request.getParameter("nombre"));
		form.setItem("apellido", request.getParameter("apellido"));
		form.setItem("email", request.getParameter("email"));
		form.setItem("dni", request.getParameter("dni"));

		try {
			if(request.getParameter("userID")!=null) {
				if(request.getParameter("passwordVer")!=null) {

					form.setItem("password", request.getParameter("passwordVer"));
					UserForm user = (UserForm) daoUser.valid(form);
					if(user!=null) {
						//Implementar update
						form.setItem("userID", request.getParameter("userID"));
						
						if(request.getParameter("password1")!=null && request.getParameter("password1").length()>0) {
							
							form.setItem("password", request.getParameter("password1"));
						}else {
							form.removeItem("password");
						}
						
						daoUser.update(form);
						user.setApellido(request.getParameter("apellido"));
						user.setNombre(request.getParameter("nombre"));
						user.setEmail(request.getParameter("email"));
						user.setDni(Integer.valueOf(request.getParameter("dni")));
						request.getSession(true).setAttribute("userData", user);
						
					}else {
						request.setAttribute("userValidation", "wu");
					}
				}else {
					request.setAttribute("userValidation", "w");
				}
			}else {
				//Implementar registro
				form.setItem("password", request.getParameter("password1"));
				daoUser.insert(form);
			}
			
			
		} catch (SQLException ex) {
			throw ex;
		}
		
		return mapping.getForwardByName("success");
	}


}

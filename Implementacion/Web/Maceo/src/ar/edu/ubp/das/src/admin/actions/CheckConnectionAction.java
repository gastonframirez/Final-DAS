package ar.edu.ubp.das.src.admin.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.admin.clients.ClientFactory;
import ar.edu.ubp.das.src.admin.clients.WSClient;
import ar.edu.ubp.das.src.admin.forms.TecnologiaForm;

public class CheckConnectionAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		Dao daoTecnologias = DaoFactory.getDao( "Tecnologia", "admin" );
		List<DynaActionForm> techs =  daoTecnologias.select(form);
		try {
			for(DynaActionForm tech : techs) {
				TecnologiaForm tf = (TecnologiaForm) tech;
				if(tf.getId().equals(request.getParameter("tecnologia"))) {
					System.out.println(tf.getJavaClass());
					WSClient comercioClient = ClientFactory.getClient(tf.getJavaClass(), "ar.edu.ubp.das.src.admin");
					if(comercioClient.testConnection("Token "+request.getParameter("authToken"), 
							request.getParameter("url"), request.getParameter("funcion")) == 1){
						request.setAttribute("res", 1);
					}else {
						request.setAttribute("res", 0);
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			request.setAttribute("res", 0);
		}
		
		
		return mapping.getForwardByName("success");
	}

}

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

public class ShowAddComercioAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		Dao daoCategorias = DaoFactory.getDao( "Categoria", "admin" );
		request.setAttribute("categorias", daoCategorias.select(form));
		
		Dao daoTecnologias = DaoFactory.getDao( "Tecnologia", "admin" );
		request.setAttribute("tecnologias", daoTecnologias.select(form));
		
		Dao daoServicios= DaoFactory.getDao( "Servicios", "admin" );
		request.setAttribute("servicios", daoServicios.select(form));
		
		if(request.getParameter("idComercio")!=null) {
			Dao daoComercio = DaoFactory.getDao( "Comercio", "admin" );
			
			form.setItem("idComercio", request.getParameter("idComercio"));
			try {
				request.setAttribute("comercio", daoComercio.select(form).get(0));
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return mapping.getForwardByName("success");
	}

}

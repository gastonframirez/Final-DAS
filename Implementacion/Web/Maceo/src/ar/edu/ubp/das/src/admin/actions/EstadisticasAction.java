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

public class EstadisticasAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("idComercio")!=null) {
			form.setItem("idComercio", request.getParameter("idComercio"));
			
			Dao daoComercios = DaoFactory.getDao( "EstadisticasComercio", "admin" );
			request.setAttribute("estadisticas", daoComercios.select(form));
			
			Dao daoGraficos = DaoFactory.getDao( "GraficosDashboard", "admin" );
			request.setAttribute("graficos", daoGraficos.select(form));
			
		}

		
		return mapping.getForwardByName("success");
	}

}

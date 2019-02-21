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

public class DashboardAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		try {
		Dao daoComercios = DaoFactory.getDao( "DatosCompletosComercio", "admin" );
		request.setAttribute("comercios", daoComercios.select(form));
		
		Dao daoEstadisticas = DaoFactory.getDao( "EstadisticasDashboard", "admin" );
		request.setAttribute("estadisticas", daoEstadisticas.select(form));
		
		Dao daoGraficos = DaoFactory.getDao( "GraficosDashboard", "admin" );
		request.setAttribute("graficos", daoGraficos.select(form));
		}catch(SQLException ex) {
			DynaActionForm formLogs = new DynaActionForm();
			Dao daoLogs = DaoFactory.getDao( "Log", "admin" );
			formLogs.setItem("logStr", "Error al intentar obtener datos para el dashboard de admin.");
			daoLogs.insert(formLogs);
		}
		
		return mapping.getForwardByName("success");
	}

}

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
			try {
				form.setItem("idComercio", request.getParameter("idComercio"));
				
				Dao daoComercios = DaoFactory.getDao( "EstadisticasComercio", "admin" );
				request.setAttribute("estadisticas", daoComercios.select(form));

				Dao daoTransacciones = DaoFactory.getDao( "TransaccionesComercio", "admin" );
				request.setAttribute("transacciones", daoTransacciones.select(form));
				
				Dao daoTransaccionesHistoricas = DaoFactory.getDao( "TransaccionesHistoricasComercio", "admin" );
				request.setAttribute("transaccionesHistoricas", daoTransaccionesHistoricas.select(form));
				
				Dao daoGraficos = DaoFactory.getDao( "GraficosDashboard", "admin" );
				request.setAttribute("graficos", daoGraficos.select(form));
				
				Dao daoGraficosComercio = DaoFactory.getDao( "GraficosComercio", "admin" );
				request.setAttribute("graficosC", daoGraficosComercio.select(form));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}

		
		return mapping.getForwardByName("success");
	}

}

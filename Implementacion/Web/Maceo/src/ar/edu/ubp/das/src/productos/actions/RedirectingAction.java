package ar.edu.ubp.das.src.productos.actions;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.productos.forms.OfertasForm;
import ar.edu.ubp.das.src.productos.forms.ProductoForm;
import ar.edu.ubp.das.src.productos.forms.RedirectOfertaForm;
import ar.edu.ubp.das.src.productos.forms.RedirectProductoForm;
import ar.edu.ubp.das.src.users.forms.UserForm;

public class RedirectingAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();;
		UserForm user = (UserForm)session.getAttribute("userData");
		if(user!=null) {
			
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			form.setItem("fecha", timeStamp);
			
			form.setItem("idTipo", request.getParameter("rtype"));
			form.setItem("idComercio", request.getParameter("rstr"));
			form.setItem("idUsuario", String.valueOf(user.getIdUser()));
		
			if(request.getParameter("idsal")!=null) {

				form.setItem("idOferta", request.getParameter("idsal"));
				Dao daoOfertas = DaoFactory.getDao( "RedirectOferta", "productos" );
				
				List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
				ofertas = daoOfertas.select(form);
				RedirectOfertaForm oferta = new RedirectOfertaForm();
				
				if(ofertas.size()>0)
					oferta = (RedirectOfertaForm)ofertas.get(0);
				
				request.setAttribute("ofertaData", oferta);	

//				System.out.println(oferta.getComercioNombre());
				
			}
			
			if(request.getParameter("idit")!=null) {
				System.out.println("en ID Producto");
				form.setItem("idProducto", request.getParameter("idit"));
				Dao daoProductos = DaoFactory.getDao( "RedirectProducto", "productos" );
				
				List<DynaActionForm> products = new LinkedList<DynaActionForm>();
				products = daoProductos.select(form);
				RedirectProductoForm producto = (RedirectProductoForm)products.get(0);
				request.setAttribute("productoData", producto);	
			}
			
			if(request.getParameter("idsal")!=null || request.getParameter("idit")!=null) {
				Dao daoTransaccion = DaoFactory.getDao( "Transaccion", "productos" );
				daoTransaccion.insert(form);
			}
		}

		return mapping.getForwardByName("success");
	}
	
}

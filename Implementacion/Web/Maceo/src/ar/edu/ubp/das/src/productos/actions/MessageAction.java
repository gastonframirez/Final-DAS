package ar.edu.ubp.das.src.productos.actions;

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
import ar.edu.ubp.das.src.productos.forms.MensajeForm;
import ar.edu.ubp.das.src.productos.forms.ServicioForm;
import ar.edu.ubp.das.src.users.forms.UserForm;


public class MessageAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		Dao daoMensajes =  DaoFactory.getDao( "Mensaje", "productos" );
		form.setItem("idUsuario", request.getParameter("userID"));
		form.setItem("idComercio", request.getParameter("comercioID"));
		form.setItem("mensaje", request.getParameter("message"));
		form.setItem("prodModel", request.getParameter("prodModel"));
		

		System.out.println("Guardando en DB local");
		try {
			daoMensajes.insert(form);
			
			DynaActionForm userFrom = new DynaActionForm();
			
			Dao daoUser =  DaoFactory.getDao( "User", "productos" );
			userFrom.setItem("idUsuario", request.getParameter("userID"));
			System.out.println("OBteniendo datos del user");
			
			List<DynaActionForm> usuarios = daoUser.select(userFrom);
			if(!usuarios.isEmpty()) {
				UserForm user = (UserForm)usuarios.get(0);
				System.out.println("Datos del user obtenidos");
				
				Dao daoComercios = DaoFactory.getDao( "Comercio", "productos" );
				DynaActionForm comForm = new DynaActionForm();
				comForm.setItem("idComercio", request.getParameter("comercioID"));
				comForm.setItem("idTipoServ", "3");
				
				List<DynaActionForm> servicios = daoComercios.select(comForm);
				
				ServicioForm servicio = (ServicioForm)servicios.get(0);
				
				WSClient comercioClient = ClientFactory.getClient(servicio.getJavaClass(), "ar.edu.ubp.das.src.admin");

				System.out.println("Conectando a servicio para publicar mensaje");

				DynaActionForm msg = new DynaActionForm();
				System.out.println(user.getApellido());
				System.out.println(user.getNombre());
				System.out.println(user.getEmail());
				System.out.println(user.getDni());
				
				msg.setItem("apellidoCliente", user.getApellido());
				msg.setItem("nombreCliente", user.getNombre());
				msg.setItem("emailCliente", user.getEmail());
				msg.setItem("dniCliente", String.valueOf(user.getDni()));
				msg.setItem("mensaje", request.getParameter("message"));
				msg.setItem("prodModel", request.getParameter("prodModel"));
				
				String mensaje = comercioClient.notificarMensaje(msg, "Token " + servicio.getAuthToken(), 
						servicio.getServiceURL(), servicio.getFuncion());
				
				if(mensaje.equals("200")) {
					System.out.println(mensaje);
					daoMensajes.delete(form);
					request.setAttribute("mensaje", "ok");
				}else {
					response.setStatus(400);
					request.setAttribute("mensaje", "conn");
				}
			}else {
				response.setStatus(400);
				request.setAttribute("mensaje", "db");
			}
			
			
		}catch(Exception e) {
			response.setStatus(400);
			request.setAttribute("mensaje", "db");
			e.printStackTrace();
		}

		
		
		return mapping.getForwardByName("success");
	}


}


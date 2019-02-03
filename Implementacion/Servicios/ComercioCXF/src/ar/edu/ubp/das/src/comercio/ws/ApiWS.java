package ar.edu.ubp.das.src.comercio.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.beans.OfertaResponseBean;
import ar.edu.ubp.das.src.beans.ResponseBean;
import ar.edu.ubp.das.src.comercio.daos.MSOfertasDao;

@WebService(targetNamespace = "http://ws.comercio.src.das.ubp.edu.ar/", portName = "ApiWSPort", serviceName = "ApiWSService")
public class ApiWS {
	
	@WebMethod(operationName = "getOfertas", action = "urn:GetOfertas")
	public List<OfertaResponseBean> getOfertas(@WebParam(name = "arg0") String authToken) {
		List<OfertaResponseBean> ofertas = new LinkedList<OfertaResponseBean>();
		List<DynaActionForm> ofertasDAF = new LinkedList<DynaActionForm>();
		
		if(authToken!=null) {
			if(authToken.indexOf("Token")!=-1 && authToken.split(" ").length==2) {
				String token = authToken.split(" ")[1];
	
				DynaActionForm daf = new DynaActionForm();
				
				daf.setItem("hashToken", token);
				
				try {
					MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
					if(dao.valid(daf)){
						System.out.println("Token valido");

						ofertasDAF = dao.select(daf);
						OfertaResponseBean oferta;
						
						for( DynaActionForm c : ofertasDAF ) {
							oferta = new OfertaResponseBean();
							oferta.setStatus("200");
							oferta.setFechaInicio(c.getItem("fechaInicio"));
							oferta.setFechaFin(c.getItem("fechaFin"));
							oferta.setModeloProducto(c.getItem("modeloProducto"));
							oferta.setPrecioOferta(Float.parseFloat(c.getItem("precioOferta")));
							ofertas.add(oferta);
						}
						
						return ofertas;
					}else {
						System.out.println("Token inexistente.");
						OfertaResponseBean err = new OfertaResponseBean();
						err.setStatus("401");
						err.setErrorMsg("Token inexistente.");
						ofertas.add(err);
						return ofertas;
					}
							
				} catch ( SQLException e ) {
					e.printStackTrace();
					OfertaResponseBean err = new OfertaResponseBean();
					err.setStatus("400");
					err.setErrorMsg("Error al obtener datos.");
					ofertas.add(err);
					return ofertas;
					
				}

			}else {
				System.out.println("Token mal formado.");
				OfertaResponseBean err = new OfertaResponseBean();
				err.setStatus("401");
				err.setErrorMsg("Token mal formado.");
				ofertas.add(err);
				return ofertas;
			}
		}else {
			System.out.println("Token no provisto.");
			OfertaResponseBean err = new OfertaResponseBean();
			err.setStatus("401");
			err.setErrorMsg("Token no provisto.");
			ofertas.add(err);
			return ofertas;
		}
	}
	
	@WebMethod(operationName = "insertTransaccion", action = "urn:InsertTransaccion")
	public ResponseBean insertTransaccion(@WebParam(name = "arg0") String authToken, @WebParam(name = "arg1") String fechaTransaccion, @WebParam(name = "arg2") String nombreCliente, 
			@WebParam(name = "arg3") String apellidoCliente, @WebParam(name = "arg4") String emailCliente, 
			@WebParam(name = "arg5") Integer dniCliente, @WebParam(name = "arg6") String tipoTransaccion, 
			@WebParam(name = "arg7") String modeloProducto, @WebParam(name = "arg8") Integer idOferta, 
			@WebParam(name = "arg9") Float precioProducto) {
		
		ResponseBean err = new ResponseBean();
		
		if(authToken!=null) {
			if(authToken.indexOf("Token")!=-1 && authToken.split(" ").length==2) {
				String token = authToken.split(" ")[1];
	
				DynaActionForm daf = new DynaActionForm();
				daf.setItem("hashToken", token);
				
				try {
					MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
					if(dao.valid(daf)){
						System.out.println("Token valido");

						if(fechaTransaccion == null || nombreCliente == null || apellidoCliente == null || emailCliente == null || 
								dniCliente == null || tipoTransaccion == null || modeloProducto == null || precioProducto == null) {
							err.setStatus("400");
							err.setErrorMsg("No se aportaron todos los datos requeridos.");
						}else {
							if(tipoTransaccion.equals("O") && idOferta==null) {
								err.setStatus("400");
								err.setErrorMsg("No se indico la oferta");
							}else {
								System.out.print("Obteniendo datos...\n");
								System.out.print(fechaTransaccion+"\n");
								System.out.print(nombreCliente+"\n");
								System.out.print(apellidoCliente+"\n");
								System.out.print(emailCliente+"\n");
								System.out.print(dniCliente+"\n");
								System.out.print(tipoTransaccion+"\n");
								System.out.print(modeloProducto+"\n");
								System.out.print(idOferta+"\n");
								System.out.print(precioProducto+"\n");
							
								daf.setItem("fechaTransaccion", fechaTransaccion);
								daf.setItem("nombreCliente", nombreCliente);
								daf.setItem("apellidoCliente", apellidoCliente);
								daf.setItem("emailCliente", emailCliente);
								daf.setItem("dniCliente", dniCliente.toString());
								daf.setItem("tipoTransaccion", tipoTransaccion);
								daf.setItem("modeloProducto", modeloProducto);		
								daf.setItem("precioProducto", precioProducto.toString());
								
								if(idOferta != null)
									daf.setItem("idOferta", idOferta.toString());
								
								dao.insert(daf);

								err.setStatus("200");
								err.setErrorMsg("OK");
							}
						}
					}else {
						System.out.println("Token inexistente.");
						err.setStatus("401");
						err.setErrorMsg("Token inexistente.");
					}
							
				} catch ( SQLException e ) {
					e.printStackTrace();
		    	    err.setStatus("400");
					err.setErrorMsg("Error al obtener datos.");
				}

			}else {
				System.out.println("Token mal formado.");
				err.setStatus("401");
				err.setErrorMsg("Token inexistente.");
			}
		}else {
			System.out.println("Token no provisto.");
			err.setStatus("401");
			err.setErrorMsg("Token no provisto.");
		}
		
		return err;
		
	}
}

package ar.edu.ubp.das.src.comercio.ws;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.beans.OfertaResponseBean;
import ar.edu.ubp.das.src.beans.ResponseBean;
import ar.edu.ubp.das.src.comercio.daos.MSOfertasDao;
import ar.edu.ubp.das.src.comercio.daos.MSTransaccionDao;

public class ApiWS {
	
	public List<OfertaResponseBean> getOfertas(String authToken) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		List<OfertaResponseBean> ofertas = new LinkedList<OfertaResponseBean>();
		List<DynaActionForm> ofertasDAF = new LinkedList<DynaActionForm>();
//		System.out.println(authToken);
		if(authToken!=null) {
			if(authToken.indexOf("Token")!=-1 && authToken.split(" ").length==2) {
//				System.out.println(authToken);
				String token = authToken.split(" ")[1];
	
				DynaActionForm daf = new DynaActionForm();
				
				daf.setItem("hashToken", token);
				
				try {
					MSOfertasDao dao = (MSOfertasDao)DaoFactory.getDao( "Ofertas", "ar.edu.ubp.das.src.comercio" );
					if(dao.valid(daf)){
//						System.out.println("Token valido");

						ofertasDAF = dao.select(daf);
						OfertaResponseBean oferta;
						
						for( DynaActionForm c : ofertasDAF ) {
							oferta = new OfertaResponseBean();
							oferta.setStatus("200");
							oferta.setFechaInicio(c.getItem("fechaInicio"));
							oferta.setFechaFin(c.getItem("fechaFin"));
							oferta.setImageURL(c.getItem("imageURL"));
							oferta.setUrl(c.getItem("ofertaURL"));
							oferta.setIdOferta(c.getItem("idOferta"));
							if(c.getItem("precioOferta")!=null) {
							}
							
							ofertas.add(oferta);
						}
					}else {
						System.out.println("Token inexistente.");
						OfertaResponseBean err = new OfertaResponseBean();
						err.setStatus("401");
						err.setErrorMsg("Token inexistente.");
						ofertas.add(err);
					}
							
				} catch ( SQLException e ) {
					e.printStackTrace();
					OfertaResponseBean err = new OfertaResponseBean();
					err.setStatus("400");
					err.setErrorMsg("Error al obtener datos.");
					ofertas.add(err);
					
				}

			}else {
				try {
					System.out.println("Token mal formado.");
					OfertaResponseBean err = new OfertaResponseBean();
					err.setStatus("401");
					err.setErrorMsg("Token mal formado.");
					ofertas.add(err);
				}catch(Exception e) {
					e.printStackTrace();
				}				
			}
		}else {
			System.out.println("Token no provisto.");
			OfertaResponseBean err = new OfertaResponseBean();
			err.setStatus("401");
			err.setErrorMsg("Token no provisto.");
			ofertas.add(err);
		}
		Thread.currentThread().setContextClassLoader(classLoader);
		return ofertas;
	}
	
	public ResponseBean notifyTransaccion(String authToken, String fechaTransaccion, String nombreCliente, 
			String apellidoCliente, String emailCliente, 
			Integer dniCliente, String tipoTransaccion, 
			String modeloProducto, Integer idOferta, 
			Float precioProducto, Float comision) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		ResponseBean err = new ResponseBean();
		
		if(authToken!=null) {
			if(authToken.indexOf("Token")!=-1 && authToken.split(" ").length==2) {
				String token = authToken.split(" ")[1];
	
				DynaActionForm daf = new DynaActionForm();
				daf.setItem("hashToken", token);
				
				try {
					MSTransaccionDao daoTransacciones = (MSTransaccionDao)DaoFactory.getDao( "Transaccion", "ar.edu.ubp.das.src.comercio" );
					if(daoTransacciones.valid(daf)){
						System.out.println("Token valido");

						if(fechaTransaccion == null || nombreCliente == null || apellidoCliente == null || emailCliente == null || 
								dniCliente == null || comision == null || (tipoTransaccion.equals("ppOffer") && idOferta == null) || 
								(tipoTransaccion.equals("ppClick") && (modeloProducto == null || precioProducto == null))) {
							err.setStatus("400");
							err.setErrorMsg("No se aportaron todos los datos requeridos.");
						}else {
							if(tipoTransaccion.equals("O") && idOferta==null) {
								err.setStatus("400");
								err.setErrorMsg("No se indico la oferta");
							}else {
								System.out.print("Obteniendo datos...\n");
								
								daf.setItem("fechaTransaccion", fechaTransaccion);
								daf.setItem("nombreCliente", nombreCliente);
								daf.setItem("apellidoCliente", apellidoCliente);
								daf.setItem("emailCliente", emailCliente);
								daf.setItem("dniCliente", dniCliente.toString());
								daf.setItem("tipoTransaccion", tipoTransaccion);
								daf.setItem("modeloProducto", modeloProducto);	
								

								if(precioProducto!=null) {
									daf.setItem("precioProducto", precioProducto.toString());
								}
								
								if(idOferta != null)
									daf.setItem("idOferta", idOferta.toString());
								
								if(comision != null)
									daf.setItem("comision", comision.toString());
								
								System.out.print(fechaTransaccion+"\n");
								System.out.print(nombreCliente+"\n");
								System.out.print(apellidoCliente+"\n");
								System.out.print(emailCliente+"\n");
								System.out.print(dniCliente+"\n");
								System.out.print(tipoTransaccion+"\n");
								System.out.print(modeloProducto+"\n");
								System.out.print(idOferta+"\n");
								System.out.print(precioProducto+"\n");
								System.out.print(comision+"\n");
								
								
								
								
								daoTransacciones.insert(daf);

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
					err.setErrorMsg("Error al ingresar datos.");
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

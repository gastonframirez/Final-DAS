package ar.edu.ubp.das.src.admin.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.security.jbcrypt.BCrypt;
import ar.edu.ubp.das.src.admin.forms.ComercioForm;
import ar.edu.ubp.das.src.admin.forms.TecnologiaForm;
import ar.edu.ubp.das.src.productos.forms.CategoriasForm;

public class MSComercioDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		try {
        	this.connectWAutoFalse();

    		this.setProcedure("dbo.saveComercio(?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("razonSocial"));
    		this.setParameter(2, form.getItem("CUIT"));
    		this.setParameter(3, form.getItem("address"));
    		this.setParameter(4, form.getItem("publicName"));  	
    		this.setParameter(5, form.getItem("phone"));
    		this.setParameter(6, form.getItem("logoURL"));
    		this.setParameter(7, Integer.parseInt(form.getItem("zipCode")));
    		
    		this.getStatement().execute();		
     	
			this.setProcedure("dbo.getComercioID(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
			this.setParameter(1, form.getItem("CUIT"));
			this.setParameter(2, form.getItem("publicName"));  	
			
			ResultSet result = this.getStatement().executeQuery();		
			result.next();
			
			Integer idComercio = -1;
			if(result!=null) {
				idComercio = result.getInt("id_comercio");
			}
			
			result.close();
			this.getStatement().close();
			
			if(idComercio>0) {
				this.autoCommit(false);
				// Ahora guardo el servicio

				if(form.getItems("tipos")!=null) {
					Map<String, Object> tipos = new HashMap<String, Object>();
					tipos = form.getItems("tipos");
					Map<String, Object> bases = new HashMap<String, Object>();
					bases = form.getItems("bases");
					Map<String, Object> funciones = new HashMap<String, Object>();
					funciones = form.getItems("funciones");
					Map<String, Object> puertos = new HashMap<String, Object>();
					puertos = form.getItems("puertos");
					
					if(tipos.size()>0) {
						for(String kTipo : tipos.keySet()) {
							System.out.println(kTipo);
							this.setProcedure("dbo.saveServicesComercio(?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							
			    			this.setParameter(1, idComercio);
			    			this.setParameter(2, Integer.parseInt(form.getItem("techID")));
				    		this.setParameter(3, bases.get(kTipo).toString());
				    		this.setParameter(4, puertos.get(kTipo).toString());
				    		this.setParameter(5, funciones.get(kTipo).toString());
				    		this.setParameter(6, kTipo);
				    		this.setParameter(7, form.getItem("authToken"));
				    		this.getStatement().execute();	
				    		this.getStatement().close();
						}
						
					}
				}else {
					//Tirar error
				}

				// Ahora guardo las comisiones
				this.setProcedure("dbo.saveComisionesComercio(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

				this.setParameter(1, idComercio);
	    		this.setParameter(2, form.getItem("ppOffer"));
	    		this.setParameter(3, form.getItem("ppProd"));
				
	    		this.getStatement().execute();		
	         					
				this.getStatement().close();
//				
				// Ahora guardo datos del scraper - URL Categorias
				
				if(form.getItems("urlCategories")!=null) {
					
					Map<String, Object> catIds = new HashMap<String, Object>();
					catIds = form.getItems("urlCategories");
					
					if(catIds.size()>0) {
						for(String kClass : catIds.keySet()) {
							if(catIds!=null && catIds.get(kClass) != null) {
				    			this.setProcedure("dbo.saveScraperURLComercio(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
								
				    			this.setParameter(1, idComercio);
					    		this.setParameter(2, kClass);
					    		this.setParameter(3, catIds.get(kClass).toString());
					    		
					    		this.getStatement().execute();	
					    		this.getStatement().close();
				    		}
						}
						
					}
				}
				
				
				
				// Ahora guardo datos sobre la config del scraper
				Map<String, Object> classes = new HashMap<String, Object>();
				classes = form.getItems("cssClasses");
				
				Map<String, Object> needsCrawl = new HashMap<String, Object>();
				needsCrawl = form.getItems("needsCrawl");
				
				Map<String, Object> searchInName = new HashMap<String, Object>();
				searchInName = form.getItems("searchInName");
				
				for(String kClass : classes.keySet()) {
					this.setProcedure("dbo.saveScraperConfigComercio(?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					this.setParameter(1, idComercio);
		    		this.setParameter(2, classes.get(kClass).toString());
		    		if(needsCrawl!=null && needsCrawl.get(kClass) != null) {
		    			this.setParameter(3, 1);
		    		}else {
		    			this.setParameter(3, 0);
		    		}
		    		if(searchInName!=null && searchInName.get(kClass) != null) {
		    			this.setParameter(4, 1);
		    		}else {
		    			this.setParameter(4, 0);
		    		}
		    		this.setParameter(5, kClass);
		    	
		    		this.getStatement().execute();	
		    		
		    		this.getStatement().close();
				}
				
			}
			
			this.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		try {
        	this.connectWAutoFalse();
        	Integer idComercio = Integer.valueOf(form.getItem("idComercio"));
        	
//        	Guardo el comercio
    		this.setProcedure("dbo.updateComercio(?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		
    		this.setParameter(1, idComercio);
    		this.setParameter(2, form.getItem("razonSocial"));
//    		System.out.println(form.getItem("razonSocial"));
    		this.setParameter(3, form.getItem("CUIT"));
//    		System.out.println(form.getItem("CUIT"));
    		this.setParameter(4, form.getItem("address"));
//    		System.out.println(form.getItem("address"));
    		this.setParameter(5, form.getItem("publicName"));  	
//    		System.out.println(form.getItem("publicName"));
    		this.setParameter(6, form.getItem("phone"));
//    		System.out.println(form.getItem("phone"));
    		this.setParameter(7, form.getItem("logoURL"));
//    		System.out.println(form.getItem("logoURL"));
    		if(form.getItem("zipCode")!=null) {
    			this.setParameter(8, Integer.parseInt(form.getItem("zipCode")));
//    			System.out.println(form.getItem("zipCode"));
    		}else {
    			this.setNull(8, Types.INTEGER);
    		}
    		
    		
    		this.getStatement().execute();		

			if(idComercio>0) {
				this.autoCommit(false);	
	    		
	    		if(form.getItems("tipos")!=null) {
					Map<String, Object> tipos = new HashMap<String, Object>();
					tipos = form.getItems("tipos");
					Map<String, Object> bases = new HashMap<String, Object>();
					bases = form.getItems("bases");
					Map<String, Object> funciones = new HashMap<String, Object>();
					funciones = form.getItems("funciones");
					Map<String, Object> puertos = new HashMap<String, Object>();
					puertos = form.getItems("puertos");
					
					if(tipos.size()>0) {
						for(String kTipo : tipos.keySet()) {
//							System.out.println(kTipo);
							this.setProcedure("dbo.updateServicesComercio(?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
							
			    			this.setParameter(1, idComercio);
			    			this.setParameter(2, Integer.parseInt(form.getItem("techID")));
				    		this.setParameter(3, bases.get(kTipo).toString());
				    		this.setParameter(4, puertos.get(kTipo).toString());
				    		this.setParameter(5, funciones.get(kTipo).toString());
				    		this.setParameter(6, kTipo);
				    		this.setParameter(7, form.getItem("authToken"));
				    		
				    		this.getStatement().execute();	
				    		this.getStatement().close();
						}
						
					}
				}else {
					//Tirar error
				}

	         	
//				this.commit();
				
				this.getStatement().close();
				
				// Ahora guardo las comisiones
				this.setProcedure("dbo.updateComisionesComercio(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

	    		this.setParameter(1, idComercio);
	    		this.setParameter(2, form.getItem("ppOffer"));
	    		this.setParameter(3, form.getItem("ppProd"));
				
	    		this.getStatement().execute();		
	         	
//				this.commit();
				
				this.getStatement().close();
//				
				// Ahora guardo datos del scraper - URL Categorias
				
				if(form.getItems("urlCategories")!=null) {
					Map<String, Object> catIds = new HashMap<String, Object>();
					catIds = form.getItems("urlCategories");
					if(catIds.size()>0) {
						for(String kClass : catIds.keySet()) {
							if(catIds!=null && catIds.get(kClass) != null) {
				    			this.setProcedure("dbo.updateScraperURLComercio(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
								System.out.println("Sin error 0: "+kClass);
								
				    			this.setParameter(1, idComercio);
					    		this.setParameter(2, kClass);
					    		this.setParameter(3, catIds.get(kClass).toString());
					    		
					    		this.getStatement().execute();	
//					    		this.commit();
					    		this.getStatement().close();
				    		}
						}
					}
				}
				
				
				
				// Ahora guardo datos sobre la config del scraper
				Map<String, Object> classes = new HashMap<String, Object>();
				classes = form.getItems("cssClasses");
				
				Map<String, Object> needsCrawl = new HashMap<String, Object>();
				needsCrawl = form.getItems("needsCrawl");
				
				Map<String, Object> searchInName = new HashMap<String, Object>();
				searchInName = form.getItems("searchInName");
				
				
				for(String kClass : classes.keySet()) {
					this.setProcedure("dbo.updateScraperConfigComercio(?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

					this.setParameter(1, idComercio);
		    		this.setParameter(2, classes.get(kClass).toString());
		    		if(needsCrawl!=null && needsCrawl.get(kClass) != null) {
		    			this.setParameter(3, 1);
		    		}else {
		    			this.setParameter(3, 0);
		    		}
		    		if(searchInName!=null && searchInName.get(kClass) != null) {
		    			this.setParameter(4, 1);
		    		}else {
		    			this.setParameter(4, 0);
		    		}
		    		this.setParameter(5, kClass);
		    	
		    		this.getStatement().execute();	
		    		
		    		this.getStatement().close();
				}
				
			}
			
			this.commit();
		} catch (SQLException e) {
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
		
		
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		try {
        	this.connectWAutoFalse();
        	
        	
//        	Guardo el comercio
    		this.setProcedure("dbo.toggleComercio(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		Integer idComercio = Integer.valueOf(form.getItem("idComercio"));
    		Integer habilitado = Integer.valueOf(form.getItem("habilitado"));
    		
    		this.setParameter(1, idComercio);
    		this.setParameter(2, habilitado);
    		
    		this.getStatement().execute();		

			this.commit();
		} catch (SQLException e) {
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		this.connect();
		
		Integer idComercio = Integer.parseInt(form.getItem("idComercio"));
		
		this.setProcedure("dbo.getDatosComercio(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, idComercio);
    	
		List<DynaActionForm> comercios = new LinkedList<DynaActionForm>();
    	
    	ComercioForm comercio = null;
    	
    	ResultSet result = this.getStatement().executeQuery();

    	
        if(result.next()) {
        	comercio = new ComercioForm();
        	comercio.setIdComercio(result.getInt("id_comercio"));
        	comercio.setRazonSocial(result.getString("razon_social"));
        	comercio.setCUIT(result.getString("CUIT"));
        	comercio.setCp(result.getInt("cp"));
        	comercio.setDireccion(result.getString("direccion"));
        	comercio.setNombre(result.getString("nombre_publico"));
        	comercio.setTelefono(result.getString("telefono"));
        	comercio.setLogoURL(result.getString("logo_url"));
        	comercio.setHabilitado(result.getBoolean("habilitado"));
        	
        }

        this.setProcedure("dbo.getServices(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, idComercio);
		Map<String, String> tipos = new HashMap<String, String>();
		Map<String, String> bases = new HashMap<String, String>();
		Map<String, String> funciones = new HashMap<String, String>();
		Map<String, String> puertos = new HashMap<String, String>();

		result = this.getStatement().executeQuery();
        
        while(result.next()) {
        	tipos.put(result.getString("tipo_nombre"), result.getString("tipo_nombre"));
        	bases.put(result.getString("tipo_nombre"), result.getString("service_url"));
        	funciones.put(result.getString("tipo_nombre"), result.getString("funcion"));
        	puertos.put(result.getString("tipo_nombre"), result.getString("puerto"));
        	comercio.setTecnologiaID(Integer.parseInt(result.getString("id_tecnologia")));
        	comercio.setAuthToken(result.getString("auth_token"));
        }
        comercio.setBaseURL(bases);
        comercio.setFuncion(funciones);
        comercio.setPort(puertos);
        

    	
        
        this.setProcedure("dbo.getValoresComisionesComercio(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, idComercio);
		
		result = this.getStatement().executeQuery();
        
        while(result.next()) {
        	if(result.getString("nombre").equals("ppClick")) {
        		comercio.setProductComm(result.getFloat("valor"));
        	}else if(result.getString("nombre").equals("ppOffer")) {
        		comercio.setOfferComm(result.getFloat("valor"));
        	}
        	
        }
        
        
        this.setProcedure("dbo.getComerciosURLScraper(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, idComercio);
        
		Map<String, String> categoriaURL = new HashMap<String, String>();
		
		result = this.getStatement().executeQuery();
        
        while(result.next()) {
        	Integer idCategoria = result.getInt("id_categoria");
        	categoriaURL.put(Integer.toString(idCategoria), result.getString("url_scrapping"));
        }
        System.out.println(categoriaURL.toString());
        comercio.setCategoriaURL(categoriaURL);
        
        
        this.setProcedure("dbo.getComerciosCSSScraper(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, idComercio);
        
		result = this.getStatement().executeQuery();
        
		Map<String, Boolean> needsCrawl = new HashMap<String, Boolean>();
		Map<String, Boolean> inTitle = new HashMap<String, Boolean>();

        while(result.next()) {
        	
        	switch (result.getString("prop_name")) {
			case "brand":
				comercio.setCssMarca(result.getString("class_name"));
				if(result.getInt("is_in_title")==1)  {
					inTitle.put(result.getString("prop_name"), true);
				}
				if(result.getInt("needs_crawl")==1) {
					needsCrawl.put(result.getString("prop_name"), true);
				}
				break;
			case "model":
				comercio.setCssModelo(result.getString("class_name"));	
				if(result.getInt("is_in_title")==1) {
					inTitle.put(result.getString("prop_name"), true);
				}
				if(result.getInt("needs_crawl")==1)  {
					needsCrawl.put(result.getString("prop_name"), true);
				}
				break;
			case "imgURL":
				comercio.setCssImgURL(result.getString("class_name"));
				if(result.getInt("needs_crawl")==1)  {
					needsCrawl.put(result.getString("prop_name"), true);
				}
				break;
			case "iterator":
				comercio.setCssIterator(result.getString("class_name"));
				break;
			case "name":
				comercio.setCssNombre(result.getString("class_name"));
				break;
			case "price":
				comercio.setCssPrecio(result.getString("class_name"));
				break;
			case "prodURL":
				comercio.setCssProdURL(result.getString("class_name"));
				break;

			default:
				break;
			} 
        	comercio.setNeedsCrawl(needsCrawl);
        	comercio.setSearchInName(inTitle);
        }
        comercios.add(comercio);
        
		this.disconnect();
    	
		return comercios;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

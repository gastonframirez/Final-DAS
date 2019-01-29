package ar.edu.ubp.das.src.admin.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
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

//        	Guardo el comercio
    		this.setProcedure("dbo.saveComercio(?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("razonSocial"));
//    		System.out.println(form.getItem("razonSocial"));
    		this.setParameter(2, form.getItem("CUIT"));
//    		System.out.println(form.getItem("CUIT"));
    		this.setParameter(3, form.getItem("address"));
//    		System.out.println(form.getItem("address"));
    		this.setParameter(4, form.getItem("publicName"));  	
//    		System.out.println(form.getItem("publicName"));
    		this.setParameter(5, form.getItem("phone"));
//    		System.out.println(form.getItem("phone"));
    		this.setParameter(6, form.getItem("logoURL"));
//    		System.out.println(form.getItem("logoURL"));
    		
    		this.getStatement().execute();		
     	
//			this.commit();

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
			
//			this.commit();


			if(idComercio>0) {
				this.autoCommit(false);
//				// Ahora guardo el servicio
				this.setProcedure("dbo.saveServicesComercio(?,?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
	    		this.setParameter(1, idComercio);
	    		this.setParameter(2, Integer.parseInt(form.getItem("techID")));
	    		this.setParameter(3, form.getItem("baseURLOffers"));
	    		this.setParameter(4, Integer.parseInt(form.getItem("portOffers")));
	    		this.setParameter(5, form.getItem("functionOffers"));  	
	    		this.setParameter(6, form.getItem("baseURLTrsct"));
	    		this.setParameter(7, Integer.parseInt(form.getItem("portTrsct")));
	    		this.setParameter(8, form.getItem("functionTrsct"));
    			this.setParameter(9, form.getItem("authToken"));
				
	    		this.getStatement().execute();		
	         	
//				this.commit();
				
				this.getStatement().close();
				
				// Ahora guardo las comisiones
				this.setProcedure("dbo.saveComisionesComercio(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

	    		this.setParameter(1, idComercio);
	    		this.setParameter(2, form.getItem("ppOffer"));
	    		this.setParameter(3, form.getItem("ppProd"));
				
	    		this.getStatement().execute();		
	         	
//				this.commit();
				
				this.getStatement().close();
//				
				// Ahora guardo datos del scraper - URL Categorias
				

				Map<String, Object> catIds = new HashMap<String, Object>();
				catIds = form.getItems("urlCategories");
				
				for(String kClass : catIds.keySet()) {
					if(catIds!=null && catIds.get(kClass) != null) {
		    			this.setProcedure("dbo.saveScraperURLComercio(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						System.out.println("Sin error 0: "+kClass);
						
		    			this.setParameter(1, idComercio);
			    		this.setParameter(2, kClass);
			    		this.setParameter(3, catIds.get(kClass).toString());
			    		
			    		this.getStatement().execute();	
//			    		this.commit();
			    		this.getStatement().close();
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
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
//		this.connect();
//		
//		this.setProcedure("dbo.getTecnologias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
//    	List<DynaActionForm> comercios = new LinkedList<DynaActionForm>();
//    	
//    	ComercioForm comercio;
//    	
//    	ResultSet result = this.getStatement().executeQuery();
//  
//        while(result.next()) {
//        	comercio = new ComercioForm();
//        	comercio.setIdComercio(result.getInt("id_comercio"));
//        	comercio.setNombre(result.getString("nombre"));
//        	comercio.setCantOffers(result.getInt("q_ffers"));
//        	comercio.setTotComisiones(result.getFloat("tot_comm"));
//        	comercio.setServiceStatus(result.getBoolean("serv_status"));
//        	comercio.setHabilitado(result.getBoolean("habilitado"));
//
//        	comercios.add(comercio);
//        }
//        
//		this.disconnect();
//		
		return null;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

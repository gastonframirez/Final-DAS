package ar.edu.ubp.das.src.orchestrator.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.orchestrator.forms.ComercioForm;

public class MSComerciosDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

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
		this.connect();
		
		this.setProcedure("dbo.getComercios", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
    	List<DynaActionForm> comercios = new LinkedList<DynaActionForm>();
    	
    	ComercioForm comercio;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	comercio = new ComercioForm();
        	comercio.setIdComercio(Integer.valueOf(result.getString("id_comercio")));
        	comercio.setNombre(result.getString("nombre_publico"));
        	comercio.setTotalCrawl(result.getBoolean("totalCrawl"));

        	//Obteniendo Datos para services
        	this.setProcedure("dbo.getServices(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	this.setParameter(1, comercio.getIdComercio());
        	
        	ResultSet resultServices = this.getStatement().executeQuery();
        	
     		Map<String, String> tipos = new HashMap<String, String>();
     		Map<String, String> bases = new HashMap<String, String>();
     		Map<String, String> funciones = new HashMap<String, String>();
     		Map<String, String> puertos = new HashMap<String, String>();

     		resultServices = this.getStatement().executeQuery();
             
             while(resultServices.next()) {
             	tipos.put(resultServices.getString("tipo_nombre"), resultServices.getString("tipo_nombre"));
             	bases.put(resultServices.getString("tipo_nombre"), resultServices.getString("service_url"));
             	funciones.put(resultServices.getString("tipo_nombre"), resultServices.getString("funcion"));
             	puertos.put(resultServices.getString("tipo_nombre"), resultServices.getString("puerto"));
             	comercio.setTecnologiaID(Integer.parseInt(resultServices.getString("id_tecnologia")));
             	comercio.setAuthToken(resultServices.getString("auth_token"));
             	comercio.setJavaClass(resultServices.getString("javaClass"));
             	
             }
             comercio.setBaseURL(bases);
             comercio.setFuncion(funciones);
             comercio.setPort(puertos);
             
        	
        	//Obteniendo URLs a scrapear
        	this.setProcedure("dbo.getComerciosURLScraper(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	this.setParameter(1, comercio.getIdComercio());
        	this.setParameter(2, 1);
        	
        	ResultSet resultScraper = this.getStatement().executeQuery();
        	
        	Map<String, String> categoriaURLs = new HashMap<String, String>();
        	
        	while(resultScraper.next()) {
            	categoriaURLs.put(resultScraper.getString("id_categoria"), resultScraper.getString("url_scrapping"));
            }
        	
        	comercio.setCategoriaURL(categoriaURLs);
        	
        	//Obteniendo estructura CSS para scrapper
        	this.setProcedure("dbo.getComerciosCSSScraper(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	this.setParameter(1, comercio.getIdComercio());
        	
        	resultScraper = this.getStatement().executeQuery();

        	Map<String, Boolean> needsCrawl = new HashMap<String, Boolean>();
        	Map<String, Boolean> searchInName = new HashMap<String, Boolean>();
        	
        	
        	while(resultScraper.next()) {
        		String propName = resultScraper.getString("prop_name");
        		String className = resultScraper.getString("class_name");
            	switch (propName) {
            	case "iterator":
					comercio.setCssIterator(className);
					break;
				case "name":
					comercio.setCssNombre(className);
					break;
				case "model":
					comercio.setCssModelo(className);
					if(resultScraper.getInt("is_in_title")==1) {
						searchInName.put(propName, true);
					}
					if(resultScraper.getInt("needs_crawl")==1) {
						needsCrawl.put(propName, true);
					}
					break;
				case "brand":
					comercio.setCssMarca(className);
					if(resultScraper.getInt("is_in_title")==1) {
						searchInName.put(propName, true);
					}
					if(resultScraper.getInt("needs_crawl")==1) {
						needsCrawl.put(propName, true);
					}
					break;
				case "price":
					comercio.setCssPrecio(className);
					break;
				case "imgURL":
					comercio.setCssImgURL(className);
					if(resultScraper.getInt("is_in_title")==1) {
						searchInName.put(propName, true);
					}
					if(resultScraper.getInt("needs_crawl")==1) {
						needsCrawl.put(propName, true);
					}
					break;
				case "prodURL":
					comercio.setCssProdURL(className);
					break;
							
				default:
					break;
				}
            }
        	comercio.setNeedsCrawl(needsCrawl);
        	comercio.setSearchInName(searchInName);
        	
        	
        	comercios.add(comercio);
        }
       
        
        
		this.disconnect();
		
		return comercios;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}

package ar.edu.ubp.das.src.scraper.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.*;
import ar.edu.ubp.das.src.scraper.forms.CategoriaForm;
import ar.edu.ubp.das.src.scraper.forms.ComercioForm;


public class Main {

	public static void main(String []args) {

		System.out.println( ">> Iniciando Scrapping de Webs - " + new Date().toString() );
		
		try {
			DynaActionForm form = new DynaActionForm();
			
			Dao categoriasDao = DaoFactory.getDao("Categorias", "ar.edu.ubp.das.src.scraper");
			List<DynaActionForm> categorias = categoriasDao.select(form);
			
			
			Dao comerciosDao = DaoFactory.getDao("Comercios", "ar.edu.ubp.das.src.scraper");
			List<DynaActionForm> comercios = comerciosDao.select(form);
			for(DynaActionForm com : comercios) {
				ComercioForm comF = (ComercioForm)com;

				Map<String, String> categories = comF.getCategoriaURLs();
				
				for(String mapUrl : categories.keySet()) {
					System.out.println(mapUrl + ": " + categories.get(mapUrl));
					try {
						final Document document = Jsoup.connect(categories.get(mapUrl)).get();
						
						for(Element prod : document.select(comF.getCssIterator())) {
							
							String title = prod.select(comF.getCssNombre()).text();
							System.out.println(title);
							
							String link = prod.select(comF.getCssProdURL()).first().absUrl("href");
							System.out.println(link);
						
							String price = prod.select(comF.getCssPrecio()).text();
							System.out.println(price);
							
							String brand = "";
							String model = "";
							String imgURL = "";
							
							Boolean needsCrawl = comF.getNeedsCrawl().get("brand")!=null || comF.getNeedsCrawl().get("model")!=null || comF.getNeedsCrawl().get("imgURL")!=null;
							
							if(needsCrawl) {
								Map<String, Elements> results = new HashMap<String, Elements>();
								HashMap<String, String> classes = new HashMap<String, String>();
								
								for(String key : comF.getNeedsCrawl().keySet()) {
									String classStr = "";
									switch (key) {
										case "brand":
											classStr = comF.getCssMarca();
											break;
										case "model":
											classStr = comF.getCssModelo();
											break;
										case "imgURL":
											classStr = comF.getCssImgURL();
											break;
										default:
											break;
									};
									classes.put(key, classStr);
//									System.out.println(key+": "+classStr);
								}
							
								Scraper scrap = new Scraper();
								results = scrap.getLoopedInfo(link, classes);
								for(String str : results.keySet()) {
									
									switch (str) {
										case "brand":
											brand = results.get(str).text();
											System.out.println(results.get(str).text());
											break;
										case "model":
											model = results.get(str).text();
											System.out.println(results.get(str).text());
											break;
										case "imgURL":
											imgURL = results.get(str).attr("src").replace(".webp_500", ".jpg");
											System.out.println(results.get(str).attr("src").replace(".webp_500", ".jpg"));
											break;
										default:
											break;
									};
								}
							}
						
							Boolean searchInName = comF.getSearchInName().get("brand")!=null || comF.getSearchInName().get("model")!=null || comF.getSearchInName().get("imgURL")!=null;

							if(searchInName) {
							
								
								List<String> excludeList = new ArrayList<>();
							    
							    excludeList.add("plateado");
							    excludeList.add("plateada");
							    excludeList.add("platinum");
							    excludeList.add("plata");
							    excludeList.add("inoxidable");
							    excludeList.add("inox.");
							    excludeList.add("inox");
							    excludeList.add("acero");
							    excludeList.add("negro");
							    excludeList.add("negra");
							    excludeList.add("blanca");
							    excludeList.add("blanco");
							    excludeList.add("azul");
							    excludeList.add("rojo");
							    excludeList.add("roja");
							    
							    excludeList.add("full");
							    
							    excludeList.add("uhd");
							    excludeList.add("hd");
							    excludeList.add("4k");
							    excludeList.add("ultra");
							    excludeList.add("gris");
							    excludeList.add("silver");
							    excludeList.add("frost");
							    excludeList.add("no");
							    excludeList.add("ap");
							    excludeList.add("ab");
							    excludeList.add(brand);
							    excludeList.add(".");
							    
							    List<String> regexList = new ArrayList<>();
							    regexList.add("\\s([0-9])+\\s?(lts)(.)?");
							    regexList.add("\\s(con)*\\s*(grill)");
							    regexList.add("\\s(para)\\s[0-9]+\\s(botellas)");
							    regexList.add("\\s[0-9]+\\s(botellas)");
							    regexList.add("\\s[0-9]+\\s(bts)");
							    regexList.add("\\s(ultra)\\s(hd)");
							    
							    String titleAux = title.toLowerCase();
							 // replace all whitespace with tabs
//						        System.out.println(titleAux.replaceAll("\\s+", "\t"));
						        
						        for(String rgx : regexList) {
						        	titleAux = titleAux.replaceAll(rgx, "");
						        }
						        for(String excl : excludeList) {
						        	titleAux = titleAux.replace(excl, "");
						        }
						        String[] titleParts = titleAux.split(" ");
						        model = titleParts[titleParts.length-1];
								System.out.println(model);
						        
//								String modelo = titleParts[titleParts.length-1];
//								System.out.println(modelo.replace("-", ""));
								
								
							}

			
							
							// Correr scrapper para todos los comercios.
							// Al final ver la fecha de actualizacion de todos los comercios y si algun producto
							// no se actualizo en ningun comercio, deshabilitarlo.
							// Si un producto estaba deshabilitado pero aparecio nuevamente, update y habilitarlo
							
							System.out.println("\n");
						}
					}catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				

				
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			final Document document = Jsoup.connect(url).get();
////			System.out.println(document.outerHtml());
//			
//			for(Element prod : document.select(".itemList .itemBox")) {
//
//				String title = prod.select(".itemBox--title").text();
//				System.out.println(title);
//				
//				String link = prod.select(".itemBox--info a").attr("href");
//				System.out.println("https://www.garbarino.com.ar"+link);
//				
//				String price = prod.select(".itemBox--price .value-item").text();
//				System.out.println(price);
//			
//				Map<String, Elements> results = new HashMap<String, Elements>();
//				HashMap<String, String> classes = new HashMap<String, String>();
//				
//				classes.put("url_img", ".gb-main-detail-gallery-grid-img-full img");
//				classes.put("marca", ".gb-breadcrumb-brand a");
//				
////				Scraper scrap = new Scraper();
////				results = scrap.getLoopedInfo("https://www.garbarino.com.ar"+link, classes);
////				
////				String imgURL = results.get("url_img").attr("src");
////				System.out.println(imgURL);
////				
////				String marca = results.get("marca").text();
////				System.out.println(marca);
//			
//				String[] titleParts = title.split(" ");
//				String modelo = titleParts[titleParts.length-1];
//				System.out.println(modelo.replace("-", ""));
//
//				
//				// Correr scrapper para todos los comercios.
//				// Al final ver la fecha de actualizacion de todos los comercios y si algun producto
//				// no se actualizo en ningun comercio, deshabilitarlo.
//				// Si un producto estaba deshabilitado pero aparecio nuevamente, update y habilitarlo
//				
//				System.out.println("\n");
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
}

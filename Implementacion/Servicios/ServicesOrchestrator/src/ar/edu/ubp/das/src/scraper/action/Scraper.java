package ar.edu.ubp.das.src.scraper.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.orchestrator.forms.ConfigsForm;
import ar.edu.ubp.das.src.orchestrator.forms.ComercioForm;
import ar.edu.ubp.das.src.orchestrator.forms.ProductForm;

public class Scraper {
	public List<ProductForm> scrap(ComercioForm comercio){
		List<ProductForm> productos = new LinkedList<ProductForm>();
		Map<String, String> categories = comercio.getCategoriaURL();
	
		for(String mapUrl : categories.keySet()) {
			System.out.println(categories.get(mapUrl));

			if(categories.get(mapUrl)!=null)
				productos.addAll(this.scrap(comercio, Integer.valueOf(mapUrl), categories.get(mapUrl), 1));
		}
		
//		for(ProductForm producto : productos) {
//			System.out.println("Nuevo producto");
//			System.out.println(producto.getNombre());
//			System.out.println(producto.getMarca());
//			System.out.println(producto.getNativeModelo());
//			System.out.println(producto.getModelo());
//			System.out.println(producto.getPrecio());
//			System.out.println(producto.getImgURL());
//			System.out.println(producto.getProdURL());
//			System.out.println("\n");
//		}
		
		return productos;
	}
	
	private List<ProductForm> scrap(ComercioForm comercio, Integer idCat, String url, Integer index){
	List<ProductForm> productos = new LinkedList<ProductForm>();

		ProductForm producto;
		try {
			Dao daoConfigs = DaoFactory.getDao( "Config", "ar.edu.ubp.das.src.orchestrator" );
			
			List<DynaActionForm> configsList = daoConfigs.select(new DynaActionForm());
			ConfigsForm configs = (ConfigsForm)configsList.get(0);
			
			List<String> excludeList = new ArrayList<>();
		    excludeList.addAll(configs.getWords());
//		    System.out.println(excludeList);
		    List<String> regexList = new ArrayList<>();
		    regexList.addAll(configs.getRegex());
//		    System.out.println(regexList);
		    
		    Document document = null;
		    
		    if(comercio.getTotalCrawl()) {
		    	System.setProperty("webdriver.chrome.driver", "/Users/gframirez/Documents/UBP/Q10/DAS/Final-DAS/Implementacion/tools/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(true);
				
				WebDriver browser = new ChromeDriver(options);
		        browser.get(url);
//		        System.out.println(browser.getPageSource());
		        document = Jsoup.parse(browser.getPageSource());
		        browser.close();       
		    }else {
				document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").get();

		    }
//			System.out.println(document);
			
			
		    
			for(Element prod : document.select(comercio.getCssIterator())) {
				if(!prod.hasClass("helperComplement") || !prod.select(comercio.getCssNombre()).text().toLowerCase().contains("combo")) {
					producto = new ProductForm();
					producto.setIdCategoria(idCat);
					producto.setIdComercio(comercio.getIdComercio());
					producto.setNombre(prod.select(comercio.getCssNombre()).text());
					producto.setProdURL(prod.select(comercio.getCssProdURL()).first().absUrl("href"));
					producto.setPrecio(Float.valueOf(prod.select(comercio.getCssPrecio()).first().text().replaceAll(",00", "").replace("$", "").replace(".", "").replace("contado", "").replace(" ", "")));
					
					Boolean needsCrawl = comercio.getNeedsCrawl().get("brand")!=null || comercio.getNeedsCrawl().get("model")!=null || comercio.getNeedsCrawl().get("imgURL")!=null;
					
					if(needsCrawl) {
						Map<String, Elements> results = new HashMap<String, Elements>();
						HashMap<String, String> classes = new HashMap<String, String>();
						
						for(String key : comercio.getNeedsCrawl().keySet()) {
							String classStr = "";
							switch (key) {
								case "brand":
									classStr = comercio.getCssMarca();
									break;
								case "model":
									classStr = comercio.getCssModelo();
									break;
								case "imgURL":
									classStr = comercio.getCssImgURL();
									break;
								default:
									break;
							};
							classes.put(key, classStr);
						}
					
						Crawler crawl = new Crawler();
						results = crawl.getLoopedInfo(producto.getProdURL(), classes);
						for(String str : results.keySet()) {
							
							switch (str) {
								case "brand":
									producto.setMarca(results.get(str).text().split(" ")[0]);
									break;
								case "model":
									producto.setNativeModelo(results.get(str).text());
									producto.setModelo(producto.getNativeModelo().replace("-", "").replace("/", ""));
									break;
								case "imgURL":
									producto.setImgURL(results.get(str).attr("src").replace(".webp_500", ".jpg"));
//									System.out.println(results.get(str).attr("src").replace(".webp_500", ".jpg"));
									break;
								default:
									break;
							};
						}
					}
				
					Boolean searchInName = comercio.getSearchInName().get("brand")!=null || comercio.getSearchInName().get("model")!=null || comercio.getSearchInName().get("imgURL")!=null;

					if(searchInName) {
						
				
						
					    excludeList.add(producto.getMarca());

					    String titleAux = producto.getNombre().toLowerCase();
				        
				        for(String rgx : regexList) {
//				        	System.out.println(rgx);
				        	titleAux = titleAux.replaceAll(rgx, "");
				        }
				        for(String excl : excludeList) {
//				        	System.out.println(excl);
				        	titleAux = titleAux.replace(excl, "");
				        }
				        String[] titleParts = titleAux.split(" ");
				        String model = titleParts[titleParts.length-1];
				        
//				        System.out.println(model);
				        producto.setNativeModelo(model);

						producto.setModelo(producto.getNativeModelo().replace("-", "").replace("/", ""));
						
					}
					
					if(producto.getImgURL()==null) {
						producto.setImgURL(prod.select(comercio.getCssImgURL()).attr("src").replace(".webp_500", ".jpg"));
					}
					if(producto.getModelo()==null) {
						producto.setNativeModelo(prod.select(comercio.getCssModelo()).text());
						producto.setModelo(producto.getNativeModelo().replace("-", "").replace("/", ""));
					}
					if(producto.getMarca()==null) {
						producto.setMarca(prod.select(comercio.getCssMarca()).text().split(" ")[0]);
					}
					
					productos.add(producto);
				}
			}
		
			List<ProductForm> prodCrawler = new LinkedList<ProductForm>();
			
			if(index<2) {
				index = 2;
				do {
					String nextPageStr = "";
					if(url.contains("?")) {
		//				nextPageStr = "&" + comercio.getNextPage() + "=";
						nextPageStr = "&page=";
					}else{
		//				nextPageStr = "?" + comercio.getNextPage() + "=";
						nextPageStr = "?page=";
					}
					
					prodCrawler = this.scrap(comercio, idCat, url+nextPageStr+index, index);
					productos.addAll(prodCrawler);
					++index;
				} while (prodCrawler.size()>0);
			}
			return productos;
			
		}catch (HttpStatusException ex) {
//			System.out.println("No hay mas");
//			ex.printStackTrace();
		}
		catch (Exception ex) {
//			System.out.println("Otro error");
		}
		
		return productos;
	}
}

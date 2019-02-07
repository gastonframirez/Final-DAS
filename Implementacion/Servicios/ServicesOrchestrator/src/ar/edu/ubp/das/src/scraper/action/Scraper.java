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

import ar.edu.ubp.das.src.orchestrator.forms.ComercioForm;
import ar.edu.ubp.das.src.orchestrator.forms.ProductForm;

public class Scraper {
	public List<ProductForm> scrap(ComercioForm comercio){
		List<ProductForm> productos = new LinkedList<ProductForm>();
		Map<String, String> categories = comercio.getCategoriaURL();
	
		for(String mapUrl : categories.keySet()) {
			System.out.println(categories.get(mapUrl));
//			System.out.println(mapUrl);
			productos = this.scrap(comercio, Integer.valueOf(mapUrl), categories.get(mapUrl), 1);
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
			final Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").get();
//			System.out.println(document);

			for(Element prod : document.select(comercio.getCssIterator())) {
				if(!prod.hasClass("helperComplement")) {
					producto = new ProductForm();
					producto.setIdCategoria(idCat);
					producto.setIdComercio(comercio.getIdComercio());
					producto.setNombre(prod.select(comercio.getCssNombre()).text());
					producto.setProdURL(prod.select(comercio.getCssProdURL()).first().absUrl("href"));
					producto.setPrecio(Float.valueOf(prod.select(comercio.getCssPrecio()).text().replaceAll(",00", "").replace("$", "").replace(".", "")));
					
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
					    excludeList.add(producto.getMarca());
					    excludeList.add(".");
					    
					    List<String> regexList = new ArrayList<>();
					    regexList.add("\\s([0-9])+\\s?(lts)(.)?");
					    regexList.add("\\s(con)*\\s*(grill)");
					    regexList.add("\\s(para)\\s[0-9]+\\s(botellas)");
					    regexList.add("\\s[0-9]+\\s(botellas)");
					    regexList.add("\\s[0-9]+\\s(bts)");
					    regexList.add("\\s(ultra)\\s(hd)");
					    
					    String titleAux = producto.getNombre().toLowerCase();
				        
				        for(String rgx : regexList) {
				        	titleAux = titleAux.replaceAll(rgx, "");
				        }
				        for(String excl : excludeList) {
				        	titleAux = titleAux.replace(excl, "");
				        }
				        String[] titleParts = titleAux.split(" ");
				        String model = titleParts[titleParts.length-1];

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

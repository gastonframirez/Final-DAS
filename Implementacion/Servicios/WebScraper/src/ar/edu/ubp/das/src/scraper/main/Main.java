package ar.edu.ubp.das.src.scraper.main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {

	public static void main(String []args) {

		System.out.println( ">> Iniciando Scrapping de Webs - " + new Date().toString() );
	
		final String url = "https://www.garbarino.com/productos/tv-led-y-smart-tv/4342";
		
		try {
			final Document document = Jsoup.connect(url).get();
//			System.out.println(document.outerHtml());
			
			for(Element prod : document.select(".itemList .itemBox")) {

				String title = prod.select(".itemBox--title").text();
				System.out.println(title);
				
				String link = prod.select(".itemBox--info a").attr("href");
				System.out.println("https://www.garbarino.com.ar"+link);
				
				String price = prod.select(".itemBox--price .value-item").text();
				System.out.println(price);
			
				Map<String, Elements> results = new HashMap<String, Elements>();
				HashMap<String, String> classes = new HashMap<String, String>();
				
				classes.put("url_img", ".gb-main-detail-gallery-grid-img-full img");
				classes.put("marca", ".gb-breadcrumb-brand a");
				
//				Scraper scrap = new Scraper();
//				results = scrap.getLoopedInfo("https://www.garbarino.com.ar"+link, classes);
//				
//				String imgURL = results.get("url_img").attr("src");
//				System.out.println(imgURL);
//				
//				String marca = results.get("marca").text();
//				System.out.println(marca);
			
				String[] titleParts = title.split(" ");
				String modelo = titleParts[titleParts.length-1];
				System.out.println(modelo.replace("-", ""));

				
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

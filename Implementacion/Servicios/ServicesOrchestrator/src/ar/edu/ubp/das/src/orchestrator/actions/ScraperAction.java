package ar.edu.ubp.das.src.orchestrator.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clients.ClientFactory;
import ar.edu.ubp.das.src.clients.WSClient;
import ar.edu.ubp.das.src.orchestrator.forms.ComercioForm;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;
import ar.edu.ubp.das.src.orchestrator.forms.ProductForm;
import ar.edu.ubp.das.src.scraper.action.Crawler;
import ar.edu.ubp.das.src.scraper.action.Scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ScraperAction {
	public void init () throws SQLException {
		
		System.out.println("Iniciando scraper para la obtencion de productos");
		//Conectarse a cada comercio
		
		DynaActionForm form = new DynaActionForm();

		Dao daoComercios = DaoFactory.getDao( "Comercios", "ar.edu.ubp.das.src.orchestrator" );
		List<DynaActionForm> comercios = daoComercios.select(form);

		Scraper scraper = new Scraper();
		
		List<ProductForm> productos = new LinkedList<ProductForm>();
		
		for(DynaActionForm com : comercios) {
			ComercioForm comF = (ComercioForm)com;
			
			productos.addAll(scraper.scrap(comF));
		}
		
		
		if(productos.size()>0) {
			System.out.println("Guardando prods");
			Dao daoProductos = DaoFactory.getDao( "Products", "ar.edu.ubp.das.src.orchestrator" );
			try {
			for(ProductForm producto : productos) {
				daoProductos.insert(producto);
//				System.out.println("Nuevo producto");
//				System.out.println(producto.getNombre());
//				System.out.println(producto.getMarca());
//				System.out.println(producto.getNativeModelo());
//				System.out.println(producto.getModelo());
//				System.out.println(producto.getPrecio());
//				System.out.println(producto.getImgURL());
//				System.out.println(producto.getProdURL());
//				System.out.println("\n");
			}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		
	}
	public void unableUnavailableProducts() {
		// Checkear si hay productos que no se actualizaron en ningun comercio
		
	}
}
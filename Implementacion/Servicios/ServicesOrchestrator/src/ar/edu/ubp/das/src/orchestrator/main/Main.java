package ar.edu.ubp.das.src.orchestrator.main;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ar.edu.ubp.das.src.orchestrator.actions.OfertasAction;
import ar.edu.ubp.das.src.orchestrator.actions.ScraperAction;
import ar.edu.ubp.das.src.orchestrator.actions.TransaccionesAction;


public class Main {
	
	public static void main(String[] args) throws IOException  {
		
		System.out.println( "Iniciando Orchestrator (Tarea programada cada X minutos) - " + new Date().toString() );
		
		Runnable runnableScraper = new Runnable() {	      
			public void run() {	        
				// Crear thread para Scraper        
				System.out.println( "Checkeando si necesario obtener productos con scraper - " + new Date().toString() );	  
				ScraperAction scraper = new ScraperAction();
				try {
					scraper.init();
					
					//Deshabilito productos que no se encuentras disponibles en ninguno de los comercios
					scraper.unableUnavailableProducts();
					
				}catch(Exception ex) {
					//Loguear error
				}
				
			}	    
		};	    	    
//		ScheduledExecutorService serviceScraper = Executors.newSingleThreadScheduledExecutor();	    
//		serviceScraper.scheduleAtFixedRate(runnableScraper, 0, 10, TimeUnit.SECONDS);

		
		Runnable runnableOffers = new Runnable() {	      
			public void run() {	        
				// Crear thread para Obtener ofertas
				System.out.println( "Obteniendo nuevas ofertas - " + new Date().toString() );	
				OfertasAction offers = new OfertasAction();
				
				try {
					offers.init();
					
					//Deshabilito ofertas que deben ser deshabilitadas por fecha o por que no estan mas disponibles
					offers.unableUnavailableOffers();
					
				}catch(Exception ex) {
					//Loguear error
					ex.printStackTrace();
				}
			}	    
		};	 
		ScheduledExecutorService serviceOffers = Executors.newSingleThreadScheduledExecutor();	    
		serviceOffers.scheduleAtFixedRate(runnableOffers, 0, 30, TimeUnit.MINUTES);
		
		
		Runnable runnablePending = new Runnable() {	      
			public void run() {	        
				// Crear thread para Enviar pending transaccionnes
				System.out.println( "Checkeando si hay transacciones pendientes de envío - " + new Date().toString() );	 
				
				TransaccionesAction transacciones = new TransaccionesAction();
				transacciones.init();
				try {
					transacciones.init();
					
				}catch(Exception ex) {
					//Loguear error
				}
			}	    
		};	 
		
		ScheduledExecutorService servicePending = Executors.newSingleThreadScheduledExecutor();	    
		servicePending.scheduleAtFixedRate(runnablePending, 0, 15, TimeUnit.MINUTES);
	}
	

}

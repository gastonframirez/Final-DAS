package ar.edu.ubp.das.src.orchestrator.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clients.ClientFactory;
import ar.edu.ubp.das.src.clients.WSClient;
import ar.edu.ubp.das.src.orchestrator.actions.OfertasAction;
import ar.edu.ubp.das.src.orchestrator.actions.ScraperAction;
import ar.edu.ubp.das.src.orchestrator.actions.TransaccionesAction;


public class Main {
	
	public static void main(String[] args) throws IOException  {
		
		System.out.println( "Iniciando Orchestrator (Tarea programada cada X minutos) - " + new Date().toString() );
		
		Runnable runnableScraper = new Runnable() {	      
			public void run() {	        
				// Crear thread para Scraper        
				System.out.println( "Es tiempo para hacer scrap: - " + new Date().toString() );	  
				ScraperAction scraper = new ScraperAction();
				try {
					scraper.init();
					
					//Deshabilito productos que no se encuentras disponibles en ninguno de los comercios
					System.out.println( "Deshabilitando productos inactivos." );	
					scraper.unableUnavailableProducts();
					
				}catch(Exception ex) {
					//Loguear error
					ex.printStackTrace();
					DynaActionForm form = new DynaActionForm();
					Dao daoLogs;
					try {
						daoLogs = DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.orchestrator" );
						form.setItem("logStr", "Error al intentar generar daos de scraper.");
						daoLogs.insert(form);
					} catch (SQLException e) {

					}
				}
				
			}	    
		};	    	    
		ScheduledExecutorService serviceScraper = Executors.newSingleThreadScheduledExecutor();	    
		serviceScraper.scheduleAtFixedRate(runnableScraper, 0, 12, TimeUnit.HOURS);

		
		Runnable runnableOffers = new Runnable() {	      
			public void run() {	        
				// Crear thread para Obtener ofertas
				System.out.println( "Obteniendo nuevas ofertas - " + new Date().toString() );	
				OfertasAction offers = new OfertasAction();
				
				try {
					offers.init();
					
					//Deshabilito ofertas que deben ser deshabilitadas por fecha o por que no estan mas disponibles
					System.out.println( "Deshabilitando ofertas inactivas." );	
					offers.unableUnavailableOffers();
					
				}catch(Exception ex) {
					//Loguear error
					ex.printStackTrace();
					DynaActionForm form = new DynaActionForm();
					Dao daoLogs;
					try {
						daoLogs = DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.orchestrator" );
						form.setItem("logStr", "Error al intentar generar daos de ofertas.");
						daoLogs.insert(form);
					} catch (SQLException e) {

					}
				}
			}	    
		};	 
		ScheduledExecutorService serviceOffers = Executors.newSingleThreadScheduledExecutor();	    
		serviceOffers.scheduleAtFixedRate(runnableOffers, 0, 30, TimeUnit.MINUTES);
		
		
		Runnable runnablePending = new Runnable() {	      
			public void run() {	        
				// Crear thread para Enviar pending transaccionnes
				System.out.println( "Comprobando si hay transacciones pendientes de envio - " + new Date().toString() );	 
				
				TransaccionesAction transacciones = new TransaccionesAction();
				try {
					transacciones.init();
					System.out.println("Las transacciones pendientes han sido enviadas.");	 
				}catch(Exception ex) {
					//Loguear error
					ex.printStackTrace();
					DynaActionForm form = new DynaActionForm();
					Dao daoLogs;
					try {
						daoLogs = DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.orchestrator" );
						form.setItem("logStr", "Error al intentar generar daos de transacciones.");
						daoLogs.insert(form);
					} catch (SQLException e) {

					}
					
				}
				
				try {
					Dao daoUser = DaoFactory.getDao( "User", "ar.edu.ubp.das.src.orchestrator" );
					daoUser.update(new DynaActionForm());
				}catch(Exception ex) {
					//Loguear error
					ex.printStackTrace();
					DynaActionForm form = new DynaActionForm();
					Dao daoLogs;
					try {
						daoLogs = DaoFactory.getDao( "Log", "ar.edu.ubp.das.src.orchestrator" );
						form.setItem("logStr", "Error al intentar desbloquear usuarios.");
						daoLogs.insert(form);
					} catch (SQLException e) {

					}
					
				}
			}	    
		};	 
		
		ScheduledExecutorService servicePending = Executors.newSingleThreadScheduledExecutor();	    
		servicePending.scheduleAtFixedRate(runnablePending, 0, 15, TimeUnit.MINUTES);
	}
	

}

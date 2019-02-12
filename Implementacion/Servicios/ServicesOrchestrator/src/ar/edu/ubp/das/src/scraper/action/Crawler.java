package ar.edu.ubp.das.src.scraper.action;

import java.util.HashMap;
import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Crawler {
	public Map<String, Elements> getLoopedInfo(String url, HashMap<String, String> cssClass)
	{		
		Map<String, Elements> results = new HashMap<String, Elements>();
		
		try {
			final Document document = Jsoup.connect(url).get();
			
			for(String cls : cssClass.keySet()) {
				if(cssClass.get(cls).length()>1)
					results.put(cls, document.select(cssClass.get(cls)));
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return results;
	}
	
	public void getProducts(String comercio) {
		
	}
}

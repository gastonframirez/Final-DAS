package ar.edu.ubp.das.src.scraper.forms;

import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ComercioForm extends DynaActionForm {
	private String ID;
	private String nombre;
	private Map<String, String> categoriaURLs;

	private String cssIterator;
	private String cssNombre;
	private String cssModelo;
	private String cssMarca;
	private String cssPrecio;
	private String cssImgURL;
	private String cssProdURL;
//	private Boolean cssImgURLNeedsCrawl;
//	private Boolean cssImgURLInTitle;
//	private Boolean cssMarcaNeedsCrawl;
//	private Boolean cssMarcaInTitle;
//	private Boolean cssModeloNeedsCrawl;
//	private Boolean cssModeloInTitle;
//	
	private Map<String, Boolean> needsCrawl;
	private Map<String, Boolean> searchInName;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Map<String, String> getCategoriaURLs() {
		return categoriaURLs;
	}
	public void setCategoriaURLs(Map<String, String> categoriaURLs) {
		this.categoriaURLs = categoriaURLs;
	}
	public String getCssNombre() {
		return cssNombre;
	}
	public void setCssNombre(String cssNombre) {
		this.cssNombre = cssNombre;
	}
	public String getCssModelo() {
		return cssModelo;
	}
	public void setCssModelo(String cssModelo) {
		this.cssModelo = cssModelo;
	}
	public String getCssMarca() {
		return cssMarca;
	}
	public void setCssMarca(String cssMarca) {
		this.cssMarca = cssMarca;
	}
	public String getCssPrecio() {
		return cssPrecio;
	}
	public void setCssPrecio(String cssPrecio) {
		this.cssPrecio = cssPrecio;
	}
	public String getCssImgURL() {
		return cssImgURL;
	}
	public void setCssImgURL(String cssImgURL) {
		this.cssImgURL = cssImgURL;
	}
	public String getCssProdURL() {
		return cssProdURL;
	}
	public void setCssProdURL(String cssProdURL) {
		this.cssProdURL = cssProdURL;
	}
	public Map<String, Boolean> getNeedsCrawl() {
		return needsCrawl;
	}
	public void setNeedsCrawl(Map<String, Boolean> needsCrawl) {
		this.needsCrawl = needsCrawl;
	}
	public Map<String, Boolean> getSearchInName() {
		return searchInName;
	}
	public void setSearchInName(Map<String, Boolean> searchInName) {
		this.searchInName = searchInName;
	}
	public String getCssIterator() {
		return cssIterator;
	}
	public void setCssIterator(String cssIterator) {
		this.cssIterator = cssIterator;
	}
	
	
}

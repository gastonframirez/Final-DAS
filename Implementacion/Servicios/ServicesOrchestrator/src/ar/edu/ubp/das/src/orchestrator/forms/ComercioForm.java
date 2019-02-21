package ar.edu.ubp.das.src.orchestrator.forms;

import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ComercioForm extends DynaActionForm {
	private Integer idComercio;
	private String nombre;
	private Boolean habilitado;
	private Boolean serviceStatus;
	
	private String authToken;

	private Map<String, String>  baseURL;
	private Map<String, String>  port;
	private	Map<String, String>  funcion;
	
	private Float offerComm;
	private Float productComm;
	
	private Map<String, String> categoriaURL;
	private String cssIterator;
	private String cssNombre;
	private String cssModelo;
	private String cssMarca;
	private String cssPrecio;
	private String cssImgURL;
	private String cssProdURL;

	private Integer tecnologiaID;
	private String javaClass;
	
	private Map<String, Boolean> needsCrawl;
	private Map<String, Boolean> searchInName;
	private Boolean totalCrawl;
	
	private String paginacion;
	private String cssPaginacion;

	
	
	public String getCssIterator() {
		return cssIterator;
	}
	public void setCssIterator(String cssIterator) {
		this.cssIterator = cssIterator;
	}
	public Integer getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Boolean getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(Boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public Float getOfferComm() {
		return offerComm;
	}
	public void setOfferComm(Float offerComm) {
		this.offerComm = offerComm;
	}
	public Float getProductComm() {
		return productComm;
	}
	public void setProductComm(Float productComm) {
		this.productComm = productComm;
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
	public Map<String, String> getCategoriaURL() {
		return categoriaURL;
	}
	public void setCategoriaURL(Map<String, String> categoriaURL) {
		this.categoriaURL = categoriaURL;
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
	public Integer getTecnologiaID() {
		return tecnologiaID;
	}
	public void setTecnologiaID(Integer tecnologiaID) {
		this.tecnologiaID = tecnologiaID;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getJavaClass() {
		return javaClass;
	}
	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}
	public Map<String, String> getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(Map<String, String> baseURL) {
		this.baseURL = baseURL;
	}
	public Map<String, String> getPort() {
		return port;
	}
	public void setPort(Map<String, String> port) {
		this.port = port;
	}
	public Map<String, String> getFuncion() {
		return funcion;
	}
	public void setFuncion(Map<String, String> funcion) {
		this.funcion = funcion;
	}
	public Boolean getTotalCrawl() {
		return totalCrawl;
	}
	public void setTotalCrawl(Boolean totalCrawl) {
		this.totalCrawl = totalCrawl;
	}
	public String getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(String paginacion) {
		this.paginacion = paginacion;
	}
	public String getCssPaginacion() {
		return cssPaginacion;
	}
	public void setCssPaginacion(String cssPaginacion) {
		this.cssPaginacion = cssPaginacion;
	}
	
	
}

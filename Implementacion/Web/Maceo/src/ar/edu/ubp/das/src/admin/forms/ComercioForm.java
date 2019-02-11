package ar.edu.ubp.das.src.admin.forms;

import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ComercioForm extends DynaActionForm {
	private Integer idComercio;
	private String nombre;
	private Integer cantOffers;
	private Boolean habilitado;
	private Float totComisiones;
	private Boolean serviceStatus;

	private String razonSocial;
	private String CUIT;
	private String direccion;
	private String provincia;
	private String ciudad;
	private Integer cp;
	private String telefono;
	private String logoURL;
	private Boolean totalCrawl;
	
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
	private Boolean cssImgURLNeedsCrawl;
	private Boolean cssImgURLInTitle;
	private Boolean cssMarcaNeedsCrawl;
	private Boolean cssMarcaInTitle;
	private Boolean cssModeloNeedsCrawl;
	private Boolean cssModeloInTitle;
	private String paginationParam;
	private String paginationNext;
	
	private Integer tecnologiaID;
	
	private Map<String, Boolean> needsCrawl;
	private Map<String, Boolean> searchInName;
	
	
	
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
	public Integer getCantOffers() {
		return cantOffers;
	}
	public void setCantOffers(Integer cantOffers) {
		this.cantOffers = cantOffers;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Float getTotComisiones() {
		return totComisiones;
	}
	public void setTotComisiones(Float totComisiones) {
		this.totComisiones = totComisiones;
	}
	public Boolean getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(Boolean serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCUIT() {
		return CUIT;
	}
	public void setCUIT(String cUIT) {
		CUIT = cUIT;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
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
	public String getPaginationParam() {
		return paginationParam;
	}
	public void setPaginationParam(String paginationParam) {
		this.paginationParam = paginationParam;
	}
	public String getPaginationNext() {
		return paginationNext;
	}
	public void setPaginationNext(String paginationNext) {
		this.paginationNext = paginationNext;
	}

	
	
}

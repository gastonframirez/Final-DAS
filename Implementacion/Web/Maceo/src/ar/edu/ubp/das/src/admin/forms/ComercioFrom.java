package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ComercioFrom extends DynaActionForm {
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
	
	private String baseURLOffers;
	private Integer portOffers;
	private	String funcionOffers;
	
	private String baseURLTransacciones;
	private Integer portTransacciones;
	private	String funcionTransacciones;
	
	private Float offerComm;
	private Float productComm;
	
	private String categoriaURL[];
	private String cssNombre;
	private String cssModelo;
	private String cssMarca;
	private String cssPrecio;
	private String cssImgURL;
	private String cssProdURL;
	private Boolean needsCrawl[];
	private Boolean searchInName[];
	
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
	public String getBaseURLOffers() {
		return baseURLOffers;
	}
	public void setBaseURLOffers(String baseURLOffers) {
		this.baseURLOffers = baseURLOffers;
	}
	public Integer getPortOffers() {
		return portOffers;
	}
	public void setPortOffers(Integer portOffers) {
		this.portOffers = portOffers;
	}
	public String getFuncionOffers() {
		return funcionOffers;
	}
	public void setFuncionOffers(String funcionOffers) {
		this.funcionOffers = funcionOffers;
	}
	public String getBaseURLTransacciones() {
		return baseURLTransacciones;
	}
	public void setBaseURLTransacciones(String baseURLTransacciones) {
		this.baseURLTransacciones = baseURLTransacciones;
	}
	public Integer getPortTransacciones() {
		return portTransacciones;
	}
	public void setPortTransacciones(Integer portTransacciones) {
		this.portTransacciones = portTransacciones;
	}
	public String getFuncionTransacciones() {
		return funcionTransacciones;
	}
	public void setFuncionTransacciones(String funcionTransacciones) {
		this.funcionTransacciones = funcionTransacciones;
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
	public String[] getCategoriaURL() {
		return categoriaURL;
	}
	public void setCategoriaURL(String[] categoriaURL) {
		this.categoriaURL = categoriaURL;
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
	public Boolean[] getSearchInName() {
		return searchInName;
	}
	public void setSearchInName(Boolean searchInName[]) {
		this.searchInName = searchInName;
	}
	public Boolean[] getNeedsCrawl() {
		return needsCrawl;
	}
	public void setNeedsCrawl(Boolean needsCrawl[]) {
		this.needsCrawl = needsCrawl;
	}
	
	
	
}

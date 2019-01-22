package ar.edu.ubp.das.src.users.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class OfertasForm extends DynaActionForm {
	private Integer idOferta;
	private String ofertaImageURL;
	private String fechaInicio;
	private String fechaFin;
	private Float precioOferta;
	private Float precioNormal;
	private Integer idProducto;
	private Integer idComercio;
	private String nombreProducto;
	private String ofertaURL;
	private String logoComercioURL;
	
	public Integer getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(Integer idOferta) {
		this.idOferta = idOferta;
	}
	public String getOfertaImageURL() {
		return ofertaImageURL;
	}
	public void setOfertaImageURL(String ofertaImageURL) {
		this.ofertaImageURL = ofertaImageURL;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Float getPrecioOferta() {
		return precioOferta;
	}
	public void setPrecioOferta(Float precioOferta) {
		this.precioOferta = precioOferta;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getOfertaURL() {
		return ofertaURL;
	}
	public void setOfertaURL(String ofertaURL) {
		this.ofertaURL = ofertaURL;
	}
	public String getLogoComercioURL() {
		return logoComercioURL;
	}
	public void setLogoComercioURL(String logoComercioURL) {
		this.logoComercioURL = logoComercioURL;
	}
	public Float getPrecioNormal() {
		return precioNormal;
	}
	public void setPrecioNormal(Float precioNormal) {
		this.precioNormal = precioNormal;
	}
	
	
	
}

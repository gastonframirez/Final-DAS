package ar.edu.ubp.das.src.productos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class OfertasForm extends DynaActionForm {
	private Integer idOferta;
	private Integer idComercio;
	private String logoComercioURL;
	private String fechaInicio;
	private String fechaFin;
	private String url;
	private String imageURL;
	private String idOfertaComercio;
	

	public Integer getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(Integer idOferta) {
		this.idOferta = idOferta;
	}
	public Integer getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	public String getLogoComercioURL() {
		return logoComercioURL;
	}
	public void setLogoComercioURL(String logoComercioURL) {
		this.logoComercioURL = logoComercioURL;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getIdOfertaComercio() {
		return idOfertaComercio;
	}
	public void setIdOfertaComercio(String idOfertaComercio) {
		this.idOfertaComercio = idOfertaComercio;
	}
	
	

	
	
}

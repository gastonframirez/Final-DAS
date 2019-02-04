package ar.edu.ubp.das.src.orchestrator.beans;

import ar.edu.ubp.das.src.orchestrator.beans.ResponseBean;

public class OfertaResponseBean extends ResponseBean{

	private String fechaInicio;
	private String fechaFin;
	private String url;
	private String imageURL;
	private String idOferta;
	private String ofertaURL;
	
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
	public String getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}
	public String getOfertaURL() {
		return ofertaURL;
	}
	public void setOfertaURL(String ofertaURL) {
		this.ofertaURL = ofertaURL;
	}
	
}

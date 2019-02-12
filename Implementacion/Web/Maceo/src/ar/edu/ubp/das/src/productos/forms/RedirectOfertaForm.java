package ar.edu.ubp.das.src.productos.forms;

import java.util.LinkedList;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class RedirectOfertaForm extends DynaActionForm {
	private String logoComercio;
	private String comercioNombre;
	private String url;
	
	public String getLogoComercio() {
		return logoComercio;
	}
	public void setLogoComercio(String logoComercio) {
		this.logoComercio = logoComercio;
	}
	public String getComercioNombre() {
		return comercioNombre;
	}
	public void setComercioNombre(String comercioNombre) {
		this.comercioNombre = comercioNombre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

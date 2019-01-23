package ar.edu.ubp.das.src.productos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class CategoriasForm extends DynaActionForm {

	private Integer idCategoria;
	private String nombre;
	private String imageURL;
	private String habilitada;
	private String lang;
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(String habilitada) {
		this.habilitada = habilitada;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
}

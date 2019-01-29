package ar.edu.ubp.das.src.admin.forms;

import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class CategoriaTransForm extends DynaActionForm {
	private Integer idCategoria;
	private String nombre;
	private String imageURL;
	private Boolean habilitada;
	private Boolean isQualifiable;
	
	private Map<String, String> translations;
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
	public Boolean getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}
	public Map<String, String> getTranslations() {
		return translations;
	}
	public void setTranslations(Map<String, String> translations) {
		this.translations = translations;
	}
	public Boolean getIsQualifiable() {
		return isQualifiable;
	}
	public void setIsQualifiable(Boolean isQualifiable) {
		this.isQualifiable = isQualifiable;
	}
	
	
	
}

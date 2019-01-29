package ar.edu.ubp.das.src.scraper.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class CategoriaForm extends DynaActionForm {
	private Integer ID;
	private String nombre;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

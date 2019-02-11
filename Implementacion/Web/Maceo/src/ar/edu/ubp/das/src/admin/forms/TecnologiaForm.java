package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class TecnologiaForm extends DynaActionForm {
	private String id;
	private String nombre;
	private String javaClass;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getJavaClass() {
		return javaClass;
	}
	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}
	
}

package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class TipoServicioForm extends DynaActionForm {
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

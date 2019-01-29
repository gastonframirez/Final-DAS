package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class EstadisticaForm extends DynaActionForm {
	private String nombre;
	private String valor;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}

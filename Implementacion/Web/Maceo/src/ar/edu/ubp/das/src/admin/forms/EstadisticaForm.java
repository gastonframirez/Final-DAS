package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class EstadisticaForm extends DynaActionForm {
	private String nombre;
	private String valor;
	private Boolean isMoney;
	private String icon;
	
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
	public Boolean getIsMoney() {
		return isMoney;
	}
	public void setIsMoney(Boolean isMoney) {
		this.isMoney = isMoney;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}

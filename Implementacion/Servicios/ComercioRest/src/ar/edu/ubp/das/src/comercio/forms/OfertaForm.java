package ar.edu.ubp.das.src.comercio.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class OfertaForm extends DynaActionForm {
	
	private String fechaInicio;
	private String fechaFin;
	private float precioOferta;
	private String modeloProducto;
	
	
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
	public float getPrecioOferta() {
		return precioOferta;
	}
	public void setPrecioOferta(float precioOferta) {
		this.precioOferta = precioOferta;
	}
	public String getModeloProducto() {
		return modeloProducto;
	}
	public void setModeloProducto(String modeloProducto) {
		this.modeloProducto = modeloProducto;
	}
}

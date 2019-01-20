package ar.edu.ubp.das.src.comercio.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class TransaccionForm extends DynaActionForm {
	private String modeloProducto;
	private Integer idOferta;
	private String tipoTransaccion;
	private String nombreCliente;
	private String apellidoCliente;
	private String emailCliente;
	private Integer dniCliente;
	private float precioProducto;
	private String fechaTransaccion;
	
	
	public String getModeloProducto() {
		return modeloProducto;
	}
	public void setModeloProducto(String modeloProducto) {
		this.modeloProducto = modeloProducto;
	}
	public Integer getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(Integer idOferta) {
		this.idOferta = idOferta;
	}
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoCliente() {
		return apellidoCliente;
	}
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public Integer getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(Integer dniCliente) {
		this.dniCliente = dniCliente;
	}
	public float getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	
	
}

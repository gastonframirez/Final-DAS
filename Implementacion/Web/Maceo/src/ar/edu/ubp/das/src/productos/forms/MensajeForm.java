package ar.edu.ubp.das.src.productos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class MensajeForm extends DynaActionForm {
	private String mensaje;
	private String nombreCliente;
	private String apellidoCliente;
	private String emailCliente;
	private String dniCliente;
	private Integer idCliente;
	private Integer idComercio;
	private String nombreProducto;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	
	
}

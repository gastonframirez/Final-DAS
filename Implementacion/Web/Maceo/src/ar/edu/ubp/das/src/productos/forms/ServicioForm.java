package ar.edu.ubp.das.src.productos.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ServicioForm extends DynaActionForm {
	private String javaClass;
	private String authToken;
	private String serviceURL;
	private String tipo;
	private String funcion;
	public String getJavaClass() {
		return javaClass;
	}
	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getServiceURL() {
		return serviceURL;
	}
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	
	
}

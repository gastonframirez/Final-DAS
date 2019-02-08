package ar.edu.ubp.das.src.productos.forms;

import java.util.LinkedList;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class RedirectProductoForm extends DynaActionForm {
	private String modelo; //Modelo
	private String nombre;
	private String imagenURL;
	private Float precio;
	private String productoURL;
	private String logoComercio;
	private String comercioNombre;
	
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagenURL() {
		return imagenURL;
	}
	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public String getProductoURL() {
		return productoURL;
	}
	public void setProductoURL(String productoURL) {
		this.productoURL = productoURL;
	}
	public String getLogoComercio() {
		return logoComercio;
	}
	public void setLogoComercio(String logoComercio) {
		this.logoComercio = logoComercio;
	}
	public String getComercioNombre() {
		return comercioNombre;
	}
	public void setComercioNombre(String comercioNombre) {
		this.comercioNombre = comercioNombre;
	}
	
}

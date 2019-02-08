package ar.edu.ubp.das.src.productos.forms;

import java.util.LinkedList;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ProductoForm extends DynaActionForm {
	private Integer idProducto;
	private String modelo;
	private String nombre;
	private LinkedList<ProductoForm> productosAlternativos;
	private String imagenURL;
	private Float precio;
	private String productoURL;
	private String logoComercio;
	private Integer idComercio;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	
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

	public LinkedList<ProductoForm> getProductosAlternativos() {
		return productosAlternativos;
	}
	public void setProductosAlternativos(LinkedList<ProductoForm> productosAlternativos) {
		this.productosAlternativos = productosAlternativos;
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
	public Integer getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	
	
	
	
}

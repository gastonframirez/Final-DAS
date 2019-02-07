package ar.edu.ubp.das.src.orchestrator.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ProductForm extends DynaActionForm {

	private String prodURL;
	private String imgURL;
	private String nombre;
	private String marca;
	private String modelo;
	private Float precio;
	private String comercio;
	private String nativeModelo;
	
	private Integer idCategoria;
	private Integer idComercio;
	
	public String getProdURL() {
		return prodURL;
	}
	public void setProdURL(String prodURL) {
		this.prodURL = prodURL;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public String getComercio() {
		return comercio;
	}
	public void setComercio(String comercio) {
		this.comercio = comercio;
	}
	public String getNativeModelo() {
		return nativeModelo;
	}
	public void setNativeModelo(String nativeModelo) {
		this.nativeModelo = nativeModelo;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Integer idComercio) {
		this.idComercio = idComercio;
	}
	
}

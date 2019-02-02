package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class TransaccionForm extends DynaActionForm {
	private Integer transaccionID;
	private String fecha;
	private Integer ofertaID;
	private Boolean pending;
	private String transNombre;
	private Float valor;
	private String prodNombre;
	private Float prodPrecio;
	public Integer getTransaccionID() {
		return transaccionID;
	}
	public void setTransaccionID(Integer transaccionID) {
		this.transaccionID = transaccionID;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getOfertaID() {
		return ofertaID;
	}
	public void setOfertaID(Integer ofertaID) {
		this.ofertaID = ofertaID;
	}
	public Boolean getPending() {
		return pending;
	}
	public void setPending(Boolean pending) {
		this.pending = pending;
	}
	public String getTransNombre() {
		return transNombre;
	}
	public void setTransNombre(String transNombre) {
		this.transNombre = transNombre;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getProdNombre() {
		return prodNombre;
	}
	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}
	public Float getProdPrecio() {
		return prodPrecio;
	}
	public void setProdPrecio(Float prodPrecio) {
		this.prodPrecio = prodPrecio;
	}
	
	
	
}

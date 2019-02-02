package ar.edu.ubp.das.src.admin.forms;

import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class HistoricoComisionForm extends DynaActionForm {
	private Map<String, Float> valuesOffer;
	private Map<String, Float> valuesProduct;
	private Map<String, Float> valuesTotal;
	public Map<String, Float> getValuesOffer() {
		return valuesOffer;
	}
	public void setValuesOffer(Map<String, Float> valuesOffer) {
		this.valuesOffer = valuesOffer;
	}
	public Map<String, Float> getValuesProduct() {
		return valuesProduct;
	}
	public void setValuesProduct(Map<String, Float> valuesProduct) {
		this.valuesProduct = valuesProduct;
	}
	public Map<String, Float> getValuesTotal() {
		return valuesTotal;
	}
	public void setValuesTotal(Map<String, Float> valuesTotal) {
		this.valuesTotal = valuesTotal;
	}
	
	
}

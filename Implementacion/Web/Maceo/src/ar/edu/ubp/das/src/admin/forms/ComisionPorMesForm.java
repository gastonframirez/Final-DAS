package ar.edu.ubp.das.src.admin.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ComisionPorMesForm extends DynaActionForm {
	private String year;
	private String month;
	private Float totalMonth;
	private Integer tipo;
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Float getTotalMonth() {
		return totalMonth;
	}
	public void setTotalMonth(Float totalMonth) {
		this.totalMonth = totalMonth;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
}

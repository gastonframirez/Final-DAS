package ar.edu.ubp.das.src.orchestrator.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class ResponseForm extends DynaActionForm {
	private String errorMsg;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}

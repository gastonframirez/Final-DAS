package ar.edu.ubp.das.src.orchestrator.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class LogForm extends DynaActionForm{
	private String logStr;

	public String getLogStr() {
		return logStr;
	}

	public void setLogStr(String logStr) {
		this.logStr = logStr;
	}
	
}

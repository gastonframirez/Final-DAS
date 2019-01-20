package ar.edu.ubp.das.mvc.config;

import java.util.HashMap;
import java.util.Map;

public final class ActionConfig {

	private String  path;
	private String  type;
	private String  form;
	private boolean validate;
	private boolean noForward;
	private Map<String, ParameterConfig> parameters;
	private Map<String, ForwardConfig>   forwards;
	
	public ActionConfig() {
		this.validate   = false;
		this.noForward  = false;
		this.parameters = new HashMap<String, ParameterConfig>();
		this.forwards   = new HashMap<String, ForwardConfig>();
	}

	public String getPath() {
		return path;
	}

	public String getType() {
		return type;
	}

	public String getForm() {
		return form;
	}

	public boolean isValidate() {
		return validate;
	}

	public boolean isNoForward() {
		return noForward;
	}
	
	public ParameterConfig getParameterByName(String name) {
		if(this.parameters.containsKey(name)) {
			return this.parameters.get(name);
		}
		return null;
	}

	public ForwardConfig getForwardByName(String name) {
		if(this.forwards.containsKey(name)) {
			return this.forwards.get(name);
		}
		return null;
	}

	public Map<String, ParameterConfig> getParameters() {
		return parameters;
	}

	public Map<String, ForwardConfig> getForwards() {
		return forwards;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public void setValidate(String validate) {
		this.validate = validate.isEmpty() ? false : validate.equals("true") ? true : false;
	}

	public void setNoForward(String noForward) {
		this.noForward = noForward.isEmpty() ? false : noForward.equals("true") ? true : false;
	}

	public void addParameter(ParameterConfig parameter) {
		this.parameters.put(parameter.getName(), parameter);
	}

	public void addForward(ForwardConfig forward) {
		this.forwards.put(forward.getName(), forward);
	}

	@Override
	public String toString() {
		return "ActionConfig [path=" + path + ", type=" + type + ", form="
				+ form + ", validate=" + validate + ", noForward=" + noForward
				+ ", parameters=" + parameters + ", forwards=" + forwards + "]";
	}
	
}

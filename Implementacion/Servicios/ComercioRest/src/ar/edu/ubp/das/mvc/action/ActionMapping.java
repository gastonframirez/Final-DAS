package ar.edu.ubp.das.mvc.action;

import java.util.HashMap;
import java.util.Map;

import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ParameterConfig;

public final class ActionMapping {

	private Map<String, ParameterConfig> parameters;
	private Map<String, ForwardConfig>   forwards;
	
	public ActionMapping() {
		this.parameters = new HashMap<String, ParameterConfig>();
		this.forwards   = new HashMap<String, ForwardConfig>();
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
	
	public void setParameters(Map<String, ParameterConfig> parameters) {
		this.parameters = parameters;
	}
	
	public void setForwards(Map<String, ForwardConfig> forwards) {
		this.forwards = forwards;
	}

	@Override
	public String toString() {
		return "ActionMapping [parameters=" + parameters + ", forwards="
				+ forwards + "]";
	}

}

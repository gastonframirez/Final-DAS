package ar.edu.ubp.das.mvc.config;

public final class ParameterConfig {

	private String name;
	private String value;

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ParameterConfig [name=" + name + ", value=" + value + "]";
	}
	
}
